<template>
  <div class="contact-page">
    <TopBar class="floating-topbar" />

    <div class="page-header">
      <div class="page-header-inner">
        <!-- <h1 class="page-title">Contact Us</h1> -->
        <!-- <p class="page-description">Get In Touch With Us</p> -->
      </div>
    </div>

    <div class="contact-content">
      <div class="contact-container">
        <div class="contact-grid">
          <div class="contact-info-section">
            <h2 class="section-title">Get In Touch With Us</h2>
            <p class="section-desc">
              We're here to help and answer any question you might have. We look forward to hearing from you.
            </p>

            <div class="info-cards">
              <div class="info-card">
                <div class="info-icon">
                  <div class="i-ep-location w-20 h-20"></div>
                </div>
                <div class="info-content">
                  <h3 class="info-title">Our Location</h3>
                  <p class="info-text">No. 99, Jinshi Road, Jinan, Shandong, China</p>
                </div>
              </div>

              <div class="info-card">
                <div class="info-icon">
                  <div class="i-ep-phone w-20 h-20"></div>
                </div>
                <div class="info-content">
                  <h3 class="info-title">Phone Number</h3>
                  <p class="info-text">+86 531 87865688</p>
                  <p class="info-text">+86 138 5316 7890</p>
                </div>
              </div>

              <div class="info-card">
                <div class="info-icon">
                  <div class="i-ep-message w-20 h-20"></div>
                </div>
                <div class="info-content">
                  <h3 class="info-title">Email Address</h3>
                  <p class="info-text">sales@zhishun.com</p>
                  <p class="info-text">info@zhishun.com</p>
                </div>
              </div>

              <div class="info-card">
                <div class="info-icon">
                  <div class="i-ep-clock w-20 h-20"></div>
                </div>
                <div class="info-content">
                  <h3 class="info-title">Working Hours</h3>
                  <p class="info-text">Monday - Friday: 8:00 AM - 6:00 PM</p>
                  <p class="info-text">Saturday: 9:00 AM - 5:00 PM</p>
                </div>
              </div>
            </div>

            <div class="social-section">
              <h3 class="social-title">Follow Us</h3>
              <div class="social-icons">
                <div class="social-icon">
                  <div class="i-ep-chat-line-round w-20 h-20"></div>
                </div>
                <div class="social-icon">
                  <div class="i-ep-chat-dot-round w-20 h-20"></div>
                </div>
                <div class="social-icon">
                  <div class="i-ep-bubble-round w-20 h-20"></div>
                </div>
                <div class="social-icon">
                  <div class="i-ep-promotion w-20 h-20"></div>
                </div>
              </div>
            </div>
          </div>

          <div class="contact-form-section">
            <div class="form-card">
              <h2 class="form-title">Send Message</h2>
              <p class="form-desc">Fill out the form below and we'll get back to you as soon as possible.</p>

              <el-form :model="contactForm" label-position="top" class="contact-form">
                <div class="form-row">
                  <el-form-item label="Your Name" required class="form-item-half">
                    <el-input v-model="contactForm.name" placeholder="Enter your name" />
                  </el-form-item>
                  <el-form-item label="Email Address" required class="form-item-half">
                    <el-input v-model="contactForm.email" placeholder="Enter your email" />
                  </el-form-item>
                </div>

                <div class="form-row">
                  <el-form-item label="Phone / WhatsApp" class="form-item-half">
                    <el-input v-model="contactForm.phone" placeholder="Enter phone or WhatsApp" />
                  </el-form-item>
                  <el-form-item label="Company" class="form-item-half">
                    <el-input v-model="contactForm.company" placeholder="Enter company name" />
                  </el-form-item>
                </div>

                <div class="form-row form-row-full">
                  <el-form-item label="Subject" class="form-item-full">
                    <el-input v-model="contactForm.subject" placeholder="Enter subject" />
                  </el-form-item>
                </div>

                <el-form-item label="Message" required>
                  <el-input
                    v-model="contactForm.message"
                    type="textarea"
                    :rows="5"
                   placeholder="Please include details like size, weight, destination port and etc., so that we can quote the best price.*"
                  />
                </el-form-item>

                <el-form-item class="submit-item">
                  <el-button type="primary" size="large" class="submit-btn" :loading="isSubmitting" @click="submitForm">
                    {{ isSubmitting ? 'Sending...' : 'Send Message' }}
                  </el-button>
                </el-form-item>
              </el-form>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="map-section">
      <div class="map-container">
        <div class="map-wrapper">
          <iframe
            class="google-map"
            src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3022.1422937950147!2d-73.98731968482413!3d40.75889497932681!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x89c25855c6480299%3A0x55194ec5a1ae072e!2sTimes%20Square!5e0!3m2!1sen!2sus!4v1620000000000!5m2!1sen!2sus"
            width="100%"
            height="400"
            style="border:0;"
            allowfullscreen
            loading="lazy"
            referrerpolicy="no-referrer-when-downgrade">
          </iframe>
        </div>
      </div>
    </div>

    <ContactFixed />

    <CustomerService />
  </div>
</template>

<script setup lang="ts">
import TopBar from '@/components/TopBar.vue'
import ContactFixed from '@/components/ContactFixed.vue'
import { reactive } from 'vue'
import { ElMessage } from 'element-plus'
import axios from 'axios'

definePage({
  name: 'contact',
  path: '/contact',
})

const contactForm = reactive({
  name: '',
  email: '',
  phone: '',
  company: '',
  subject: '',
  message: '',
})

const isSubmitting = ref(false)

async function submitForm() {
  if (isSubmitting.value) return

  if (!contactForm.name || !contactForm.email || !contactForm.message) {
    ElMessage.warning('Please fill in all required fields')
    return
  }

  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
  if (!emailRegex.test(contactForm.email)) {
    ElMessage.warning('Please enter a valid email address')
    return
  }

  isSubmitting.value = true

  try {
    const response = await axios.post('/api/contact', {
      name: contactForm.name,
      email: contactForm.email,
      phone: contactForm.phone,
      company: contactForm.company,
      subject: contactForm.subject,
      message: contactForm.message,
      timestamp: Date.now()
    })

    if (response.data.code === 0) {
      ElMessage.success(response.data.data || 'Message sent successfully! We will get back to you soon.')
      contactForm.name = ''
      contactForm.email = ''
      contactForm.phone = ''
      contactForm.company = ''
      contactForm.subject = ''
      contactForm.message = ''
    } else if (response.data.code === 40002) {
      ElMessage.warning(response.data.message || 'Duplicate request detected')
    } else {
      ElMessage.error(response.data.message || 'Failed to send message. Please try again.')
    }
  } catch (error) {
    console.error('Failed to submit form:', error)
    ElMessage.error('Failed to send message. Please try again.')
  } finally {
    isSubmitting.value = false
  }
}
</script>

<style scoped>

.floating-topbar {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  z-index: 100;
}

.contact-page {
  min-height: 100vh;
  background: #f5f7fa;
}

.page-header {
  background: url('/images/product/image.png') center/cover no-repeat;
  padding: 80px 0 60px;
  position: relative;
}

.page-header::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
}

.page-header-inner {
  max-width: 1400px;
  margin: 0 auto;
  padding: 0 40px;
  text-align: center;
  position: relative;
  z-index: 1;
}

.page-title {
  font-size: 48px;
  font-weight: 700;
  color: #ffffff;
  margin: 0 0 16px 0;
  line-height: 1.2;
}

.page-description {
  font-size: 18px;
  color: #ffffff;
  margin: 0;
  line-height: 1.6;
}

.contact-content {
  padding: 30px 48px 64px;
}

.contact-container {
  max-width: 1280px;
  margin: 0 auto;
}

.contact-grid {
  display: grid;
  grid-template-columns: 1fr 1.2fr;
  gap: 40px;
}

.contact-info-section {
  background: linear-gradient(135deg, #FF0000 0%, #cc0000 100%);
 /** border-radius: 16px; */
  padding: 36px;
  box-shadow: 0 4px 20px rgba(255, 0, 0, 0.15);
}

.section-title {
  font-size: 28px;
  font-weight: 700;
  color: #ffffff;
  margin: 0 0 12px 0;
  padding-bottom: 16px;
  border-bottom: 3px solid rgba(255, 255, 255, 0.3);
  position: relative;
}

.section-title::before {
  content: '';
  position: absolute;
  left: 0;
  bottom: -3px;
  width: 60px;
  height: 3px;
  background: #ffffff;
}

.section-desc {
  font-size: 15px;
  color: rgba(255, 255, 255, 0.9);
  line-height: 1.7;
  margin: 0 0 28px 0;
}

.info-cards {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.info-card {
  display: flex;
  align-items: flex-start;
  gap: 16px;
  padding: 20px;
  background: rgba(255, 255, 255, 0.15);
 /** border-radius: 12px; */
  transition: all 0.3s ease;
}

.info-card:hover {
  background: rgba(255, 255, 255, 0.25);
  transform: translateX(4px);
}

.info-icon {
  width: 52px;
  height: 52px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: rgba(255, 255, 255, 0.2);
  border-radius: 12px;
  color: #ffffff;
  flex-shrink: 0;
}

.info-content {
  flex: 1;
}

.info-title {
  font-size: 16px;
  font-weight: 600;
  color: #ffffff;
  margin: 0 0 6px 0;
}

.info-text {
  font-size: 14px;
  color: rgba(255, 255, 255, 0.85);
  margin: 0;
  line-height: 1.6;
}

.social-section {
  margin-top: 32px;
  padding-top: 28px;
  border-top: 1px solid rgba(255, 255, 255, 0.2);
}

.social-title {
  font-size: 18px;
  font-weight: 600;
  color: #ffffff;
  margin: 0 0 16px 0;
}

.social-icons {
  display: flex;
  gap: 12px;
}

.social-icon {
  width: 44px;
  height: 44px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: rgba(255, 255, 255, 0.2);
  border-radius: 50px;
  color: #ffffff;
  cursor: pointer;
  transition: all 0.3s ease;
}

.social-icon:hover {
  background: #ffffff;
  color: #FF0000;
  transform: translateY(-3px);
}

.contact-form-section {
  background: #ffffff;
  /** border-radius: 16px; */
  padding: 36px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.06);
}

.form-card {
  padding: 0 12px;
}

.form-title {
  font-size: 28px;
  font-weight: 700;
  color: #1a2a4a;
  margin: 0 0 8px 0;
  padding-bottom: 16px;
  border-bottom: 3px solid #FF0000;
  position: relative;
}

.form-title::before {
  content: '';
  position: absolute;
  left: 0;
  bottom: -3px;
  width: 60px;
  height: 3px;
  background: #FF0000;
}

.form-desc {
  font-size: 14px;
  color: #718096;
  margin: 0 0 28px 0;
}

.contact-form {
  margin-top: 24px;
}

.form-row {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 20px;
}

.form-row-full {
  grid-template-columns: 1fr;
}

.form-item-full {
  width: 100%;
}

.form-row-full {
  grid-template-columns: 1fr;
}

.form-item-full {
  width: 100%;
}

:deep(.el-form-item) {
  margin-bottom: 20px;
}

:deep(.el-form-item__label) {
  font-weight: 500;
  color: #1a2a4a;
  font-size: 14px;
}

:deep(.el-input__wrapper),
:deep(.el-textarea__wrapper) {
  border-radius: 8px;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.08);
  transition: all 0.3s ease;
  background-color: #F6F6F6;
}

:deep(.el-input__wrapper:hover),
:deep(.el-textarea__wrapper:hover) {
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.12);
  background-color: #F0F0F0;
}

:deep(.el-input__wrapper:focus-within),
:deep(.el-textarea__wrapper:focus-within) {
  box-shadow: 0 2px 12px rgba(255, 0, 0, 0.15);
  border-color: #FF0000;
  background-color: #FFFFFF;
}

.submit-item {
  margin-top: 12px;
  margin-bottom: 0 !important;
}

.submit-btn {
  width: 100%;
  height: 52px;
  font-size: 16px;
  font-weight: 600;
  border-radius: 10px;
  background: linear-gradient(135deg, #FF0000 0%, #cc0000 100%);
  border: none;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 10px;
  transition: all 0.3s ease;
}

.submit-btn:hover {
  background: linear-gradient(135deg, #cc0000 0%, #aa0000 100%);
  transform: translateY(-2px);
  box-shadow: 0 4px 16px rgba(255, 0, 0, 0.3);
}

.map-section {
  padding: 0 48px 64px;
}

.map-container {
  max-width: 1280px;
  margin: 0 auto;
}

.map-wrapper {
  height: 400px;
  /* border-radius: 16px; */
  overflow: hidden;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
}

.google-map {
  width: 100%;
  height: 100%;
  border: 0;
}

@media (max-width: 1024px) {
  .contact-grid {
    grid-template-columns: 1fr;
  }

  .contact-content,
  .map-section {
    padding: 0 24px 48px;
  }
}

@media (max-width: 768px) {
  .hero-title {
    font-size: 36px;
  }

  .hero-subtitle {
    font-size: 16px;
  }

  .hero-section {
    height: 260px;
  }

  .form-row {
    grid-template-columns: 1fr;
  }

  .section-title,
  .form-title {
    font-size: 24px;
  }

  .contact-info-section,
  .contact-form-section {
    padding: 24px;
  }
}
</style>
