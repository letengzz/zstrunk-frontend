<template>
  <div class="contact-fixed">
    <div
      class="contact-item"
      v-for="(contact, index) in contacts"
      :key="contact.type"
      :style="{ bottom: (index * 62) + 'px' }"
      @mouseenter="activeContact = contact.type"
      @mouseleave="activeContact = ''"
    >
      <div class="contact-icon-wrapper">
        <div :class="['contact-icon', contact.iconClass]"></div>
      </div>
      <div class="contact-detail" :class="{ 'show': activeContact === contact.type }">
        <span class="contact-type">{{ contact.label }}</span>
        <a 
          v-if="contact.type === 'phone'" 
          :href="`tel:${contact.value}`" 
          class="contact-value"
          @click.stop
        >{{ contact.value }}</a>
        <a 
          v-else-if="contact.type === 'email'" 
          :href="`mailto:${contact.value}`" 
          class="contact-value"
          @click.stop
        >{{ contact.value }}</a>
        <a 
          v-else-if="contact.type === 'whatsapp'" 
          :href="`https://wa.me/${contact.value.replace(/[^0-9]/g, '')}`" 
          target="_blank"
          class="contact-value"
          @click.stop
        >{{ contact.value }}</a>
        <span v-else class="contact-value">{{ contact.value }}</span>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'

interface Contact {
  type: string
  label: string
  value: string
  iconClass: string
}

const props = withDefaults(defineProps<{
  phone?: string
  email?: string
  whatsapp?: string
}>(), {
  phone: '+86-15588751133',
  email: 'contact@example.com',
  whatsapp: '86-15588751133'
})

const contacts = computed<Contact[]>(() => [
  {
    type: 'phone',
    label: 'Phone',
    value: props.phone,
    iconClass: 'i-ep-phone'
  },
  {
    type: 'email',
    label: 'Email',
    value: props.email,
    iconClass: 'i-ep-message'
  },
  {
    type: 'whatsapp',
    label: 'WhatsApp',
    value: props.whatsapp,
    iconClass: 'i-ant-design-whats-app-outlined'
  }
])

const activeContact = ref('')
</script>

<style scoped>
.contact-fixed {
  position: fixed;
  right: 20px;
  bottom: 180px;
  width: 50px;
  height: 240px;
  z-index: 1000;
}

.contact-item {
  position: absolute;
  right: 0;
  display: flex;
  align-items: center;
  height: 50px;
  border-radius: 12px;
  background: #FF4500;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
  cursor: pointer;
  transition: all 0.3s ease;
  overflow: hidden;
  width: 50px;
}

.contact-item:hover {
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.15);
  width: 210px;
   background: #FF4500;
}

.contact-icon-wrapper {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 50px;
  height: 50px;
  flex-shrink: 0;
  background: #FF4500;
  border-radius: 12px;
  transition: transform 0.3s ease;
}

.contact-icon {
  font-size: 24px;
  color: #fff;
}

.contact-detail {
  display: flex;
  flex-direction: column;
  justify-content: center;
  padding-left: 16px;
  padding-right: 16px;
  width: 0;
  overflow: hidden;
  opacity: 0;
  transition: all 0.3s ease;
  white-space: nowrap;
  flex-shrink: 0;
}

.contact-detail.show {
  width: 140px;
  opacity: 1;
}

.contact-type {
  font-size: 12px;
  color: #fff;
}

.contact-value {
  font-size: 14px;
  color: #303133;
  font-weight: 500;
  text-decoration: none;
  cursor: pointer;
}

.contact-value:hover {
  text-decoration: underline;
}

.contact-item:hover .contact-icon-wrapper {
  transform: translateX(5px);
}
</style>
