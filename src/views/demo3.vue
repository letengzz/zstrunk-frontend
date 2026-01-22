<template>
  <div class="demo3-container">
    <h1>联系方式固定组件演示</h1>
    <p>鼠标悬停在右下角的联系方式盒子查看效果</p>
    <ContactFixed 
      phone="138-0013-8800" 
      email="service@example.com" 
      whatsapp="+1 234-567-8900"
    />
  </div>
</template>

<script setup lang="ts">
import ContactFixed from '@/components/ContactFixed.vue'
</script>

<style scoped>
.demo3-container {
  min-height: 100vh;
  padding: 20px;
  background: #f5f7fa;
}

h1 {
  margin-bottom: 10px;
  color: #303133;
}

p {
  color: #606266;
}
</style>
