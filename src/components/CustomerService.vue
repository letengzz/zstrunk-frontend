<template>
  <div class="customer-service">
    <transition name="slide-up">
      <div v-if="isOpen" class="chat-panel">
        <div class="chat-header" @click="closeChat">
          <span class="chat-title">Customer Service</span>
          <span class="chat-close i-ep-close"></span>
        </div>
        <div class="chat-messages" ref="messagesContainer">
          <div
            v-for="(msg, index) in messages"
            :key="index"
            :class="['message', msg.type]"
          >
            <div class="message-content">{{ msg.content }}</div>
            <div class="message-time">{{ msg.time }}</div>
          </div>
        </div>
        <div class="chat-input-area">
          <input
            v-model="newMessage"
            type="text"
            placeholder="Type a message..."
            class="chat-input"
            @keyup.enter="sendMessage"
          />
          <button class="chat-send-btn" @click="sendMessage">
            <span class="i-ep-position"></span>
          </button>
        </div>
      </div>
    </transition>
    <div class="chat-toggle" @click="toggleChat">
      <span :class="isOpen ? 'i-ep-close' : 'i-ep-service'"></span>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, nextTick, onMounted, onUnmounted } from 'vue'

interface Message {
  type: 'customer' | 'service'
  content: string
  time: string
}

interface ChatSessionData {
  messages: Message[]
  startTime: string
}

const isOpen = ref(false)
const newMessage = ref('')
const messagesContainer = ref<HTMLElement | null>(null)
const messages = ref<Message[]>([])
const sessionStartTime = ref<string>('')
const hasUserSentMessage = ref(false)
let inactivityTimer: ReturnType<typeof setTimeout> | null = null
const SESSION_STORAGE_KEY = 'customer_service_session'

function getCurrentTime(): string {
  const now = new Date()
  return now.toLocaleTimeString('en-US', { hour: '2-digit', minute: '2-digit' })
}

function getFullDateTime(): string {
  const now = new Date()
  return now.toLocaleString('en-US', {
    year: 'numeric',
    month: 'short',
    day: 'numeric',
    hour: '2-digit',
    minute: '2-digit',
    hour12: false
  })
}

function saveSessionToStorage() {
  if (messages.value.length > 0) {
    const sessionData: ChatSessionData = {
      messages: messages.value,
      startTime: sessionStartTime.value
    }
    sessionStorage.setItem(SESSION_STORAGE_KEY, JSON.stringify(sessionData))
  }
}

function loadSessionFromStorage() {
  const saved = sessionStorage.getItem(SESSION_STORAGE_KEY)
  if (saved) {
    try {
      const sessionData: ChatSessionData = JSON.parse(saved)
      messages.value = sessionData.messages
      sessionStartTime.value = sessionData.startTime
      hasUserSentMessage.value = true
      sessionStorage.removeItem(SESSION_STORAGE_KEY)
      return true
    } catch (e) {
      console.error('Failed to load session:', e)
      return false
    }
  }
  return false
}

function resetInactivityTimer() {
  if (inactivityTimer) {
    clearTimeout(inactivityTimer)
  }
  inactivityTimer = setTimeout(() => {
    if (isOpen.value && messages.value.length > 0) {
      sendChatTranscript()
    }
  }, 10 * 60 * 1000)
}

async function sendChatTranscript() {
  if (messages.value.length === 0) return

  const transcriptData = {
    messages: messages.value,
    startTime: sessionStartTime.value,
    endTime: getCurrentTime()
  }

  try {
    const blob = new Blob([JSON.stringify(transcriptData)], { type: 'application/json' })
    navigator.sendBeacon('/api/contact/chat-transcript', blob)
    messages.value = []
    sessionStartTime.value = ''
  } catch (error) {
    console.error('Failed to send chat transcript:', error)
    saveSessionToStorage()
  }
}

function startChatSession() {
  if (sessionStartTime.value === '') {
    hasUserSentMessage.value = false
    sessionStartTime.value = getFullDateTime()
    messages.value = [{
      type: 'service',
      content: 'Hello! How can I help you today?',
      time: getCurrentTime()
    }]
  }
  resetInactivityTimer()
}

function toggleChat() {
  isOpen.value = !isOpen.value
  if (isOpen.value) {
    const hasLoadedSession = loadSessionFromStorage()
    if (!hasLoadedSession) {
      startChatSession()
    }
    nextTick(() => {
      scrollToBottom()
    })
  }
}

function closeChat() {
  if (hasUserSentMessage.value && messages.value.length > 0) {
    sendChatTranscript()
  }
  isOpen.value = false
  if (inactivityTimer) {
    clearTimeout(inactivityTimer)
    inactivityTimer = null
  }
}

function sendMessage() {
  const content = newMessage.value.trim()
  if (!content) return

  hasUserSentMessage.value = true
  messages.value.push({
    type: 'customer',
    content,
    time: getCurrentTime()
  })

  newMessage.value = ''
  resetInactivityTimer()

  nextTick(() => {
    scrollToBottom()
    setTimeout(() => {
      messages.value.push({
        type: 'service',
        content: 'Thank you for your message. Our customer service team will respond shortly.',
        time: getCurrentTime()
      })
      nextTick(() => {
        scrollToBottom()
      })
    }, 1000)
  })
}

function scrollToBottom() {
  if (messagesContainer.value) {
    messagesContainer.value.scrollTop = messagesContainer.value.scrollHeight
  }
}

function handlePageUnload() {
  saveSessionToStorage()
}

onMounted(() => {
  window.addEventListener('beforeunload', handlePageUnload)
  window.addEventListener('pagehide', handlePageUnload)
})

onUnmounted(() => {
  window.removeEventListener('beforeunload', handlePageUnload)
  window.removeEventListener('pagehide', handlePageUnload)
  if (inactivityTimer) {
    clearTimeout(inactivityTimer)
  }
})
</script>

<style scoped>
.customer-service {
  position: fixed;
  right: 20px;
  bottom: 30px;
  z-index: 1001;
  display: flex;
  flex-direction: column;
  align-items: flex-end;
}

.chat-toggle {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  background: #FF4500;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.15);
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  transition: all 0.3s ease;
  font-size: 24px;
  color: white;
}

.chat-toggle:hover {
  transform: scale(1.1);
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.2);
}

.chat-panel {
  position: absolute;
  bottom: 70px;
  right: 0;
  width: 320px;
  height: 420px;
  background: white;
  border-radius: 16px;
  box-shadow: 0 4px 24px rgba(0, 0, 0, 0.15);
  display: flex;
  flex-direction: column;
  overflow: hidden;
}

.chat-header {
  padding: 16px 20px;
  background: #FF4500;
  color: white;
  display: flex;
  justify-content: space-between;
  align-items: center;
  cursor: pointer;
}

.chat-title {
  font-size: 16px;
  font-weight: 600;
}

.chat-close {
  font-size: 18px;
  cursor: pointer;
}

.chat-messages {
  flex: 1;
  padding: 16px;
  overflow-y: auto;
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.message {
  max-width: 85%;
  display: flex;
  flex-direction: column;
}

.message.service {
  align-self: flex-start;
}

.message.customer {
  align-self: flex-end;
}

.message-content {
  padding: 12px 16px;
  border-radius: 16px;
  font-size: 14px;
  line-height: 1.5;
}

.message.service .message-content {
  background: #f5f5f5;
  color: #333;
  border-bottom-left-radius: 4px;
}

.message.customer .message-content {
  background: #FF4500;
  color: white;
  border-bottom-right-radius: 4px;
}

.message-time {
  font-size: 11px;
  color: #999;
  margin-top: 4px;
}

.message.customer .message-time {
  text-align: right;
}

.chat-input-area {
  padding: 12px 16px;
  border-top: 1px solid #eee;
  display: flex;
  gap: 8px;
}

.chat-input {
  flex: 1;
  padding: 10px 14px;
  border: 1px solid #ddd;
  border-radius: 20px;
  outline: none;
  font-size: 14px;
  transition: border-color 0.3s;
}

.chat-input:focus {
  border-color: #FF4500;
}

.chat-send-btn {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  background: #FF4500;
  border: none;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  transition: all 0.3s ease;
  color: white;
  font-size: 16px;
  transform: rotate(-45deg);
}

.chat-send-btn:hover {
  background: #e63e00;
}

.slide-up-enter-active,
.slide-up-leave-active {
  transition: all 0.3s ease;
}

.slide-up-enter-from,
.slide-up-leave-to {
  opacity: 0;
  transform: translateY(20px);
}
</style>
