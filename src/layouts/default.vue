<template>
  <div>
    <header class="header">
     
    </header>
    <router-view></router-view>
  </div>
</template>

<script setup lang="ts"></script>

<style scoped>
.header {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 1000;
  background: rgba(255, 255, 255, 0.95);
  backdrop-filter: blur(20px);
  -webkit-backdrop-filter: blur(20px);
  box-shadow: 0 2px 20px rgba(0, 0, 0, 0.08);
}

.header-inner {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 30px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 80px;
}

.header-left {
  display: flex;
  align-items: center;
}

.logo {
  display: flex;
  align-items: center;
  gap: 12px;
}

.logo-icon {
  font-size: 36px;
}

.logo-content {
  display: flex;
  flex-direction: column;
}

.logo-text {
  font-size: 24px;
  font-weight: 700;
  color: #1a1a2e;
  letter-spacing: 1px;
}

.logo-tagline {
  font-size: 11px;
  color: #4fc3f7;
  text-transform: uppercase;
  letter-spacing: 2px;
  font-weight: 600;
  margin-top: 2px;
}

.header-right {
  display: flex;
  align-items: center;
  gap: 30px;
}

.contact-item {
  display: flex;
  align-items: center;
  gap: 12px;
}

.contact-icon {
  width: 44px;
  height: 44px;
  background: linear-gradient(135deg, #4fc3f7, #29b6f6);
  border-radius: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
}

.contact-text {
  display: flex;
  flex-direction: column;
}

.contact-label {
  font-size: 12px;
  color: #888;
  font-weight: 500;
}

.contact-value {
  font-size: 15px;
  color: #1a1a2e;
  font-weight: 600;
}

.divider {
  width: 1px;
  height: 40px;
  background: rgba(0, 0, 0, 0.1);
}

@media (max-width: 768px) {
  .divider {
    display: none;
  }

  .contact-item:last-child {
    display: none;
  }

  .header-right {
    gap: 15px;
  }

  .logo-tagline {
    display: none;
  }

  .logo-text {
    font-size: 20px;
  }

  .logo-icon {
    font-size: 28px;
  }
}
</style>
