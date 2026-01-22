<template>
  <div class="floating-contact">
    <div class="contact-item">
      <div class="contact-icon i-ep-phone"></div>
      <div class="contact-info">
        <span class="contact-label">电话</span>
        <span class="contact-value">{{ phone }}</span>
      </div>
    </div>
    <div class="contact-item">
      <div class="contact-icon i-ep-message"></div>
      <div class="contact-info">
        <span class="contact-label">邮件</span>
        <span class="contact-value">{{ email }}</span>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
withDefaults(defineProps<{
  phone?: string
  email?: string
}>(), {
  phone: '400-123-4567',
  email: 'contact@example.com'
})
</script>

<style scoped>
.floating-contact {
  position: fixed;
  right: 20px;
  bottom: 100px;
  display: flex;
  flex-direction: column;
  gap: 12px;
  z-index: 1000;
}

.contact-item {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 12px 16px;
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
  cursor: pointer;
  transition: all 0.3s ease;
}

.contact-item:hover {
  transform: translateX(-4px);
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.15);
}

.contact-icon {
  font-size: 20px;
  color: #409eff;
}

.contact-info {
  display: flex;
  flex-direction: column;
}

.contact-label {
  font-size: 12px;
  color: #909399;
}

.contact-value {
  font-size: 14px;
  color: #303133;
  font-weight: 500;
}
</style>
