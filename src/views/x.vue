<template>
  <div class="carousel-container">
    <div class="logo-section">
      <img src="/public/images/logo.svg" alt="Logo" class="logo">
      <span class="company-name">XXXX</span>
      <el-menu
        mode="horizontal"
        :ellipsis="false"
        class="logo-menu"

      >
        <el-sub-menu index="1">
          <template #title>服务</template>
          <el-menu-item index="1-1">维修保养</el-menu-item>
          <el-menu-item index="1-2">零部件更换</el-menu-item>
          <el-menu-item index="1-3">检测服务</el-menu-item>
        </el-sub-menu>
          <el-sub-menu index="2">
          <template #title>服务</template>
          <el-menu-item index="1-1">维修保养</el-menu-item>
          <el-menu-item index="1-2">零部件更换</el-menu-item>
          <el-menu-item index="1-3">检测服务</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="3">
          <template #title>服务</template>
          <el-menu-item index="1-1">维修保养</el-menu-item>
          <el-menu-item index="1-2">零部件更换</el-menu-item>
          <el-menu-item index="1-3">检测服务</el-menu-item>
        </el-sub-menu>
      </el-menu>
    </div>
    <div class="logo-section right-section">
      <div h70 w70 class="i-ant-design-align-center-outlined"></div>
    </div>
    <el-carousel
    ref="carouselRef"
       height="860px"
       indicator-position="none"
      arrow="never"
      :autoplay="true"
      :pause-on-hover="false"
                  @mousedown="handleMouseDown"
      @mousemove="handleMouseMove"
      @mouseup="handleMouseUp"
      @mouseleave="handleMouseUp"
      @click="resumeAutoplay"
       >
      <el-carousel-item v-for="item in imageCount" :key="item">
        <img :src="`/images/carousel/carousel${item}.png`" alt="">
        <div class="overlay"></div>
        <div class="title-container">
          <h1 class="main-title hello-title">Hello</h1>
          <h1 class="main-title world-title">World</h1>
        </div>
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script setup lang="ts">
const imageCount = ref(0);

const loadCarouselImages = async () => {
  const modules = import.meta.glob('/public/images/carousel/*.{png,jpg,jpeg,gif,svg,webp}');
  const files = await Promise.all(Object.keys(modules));
  imageCount.value = files.length;
};

onMounted(() => {
  loadCarouselImages();
});
let startX = 0
let isDragging = false
const minSwipeDistance = 50
const carouselRef = ref()
const handleMouseDown = (e: MouseEvent) => {
  startX = e.clientX
  isDragging = true
}

const handleMouseMove = (e: MouseEvent) => {
  if (!isDragging) return

  const currentX = e.clientX
  const diffX = currentX - startX

  if (Math.abs(diffX) >= minSwipeDistance) {
    if (diffX > 0) {
      carouselRef.value?.prev()
    } else {
      carouselRef.value?.next()
    }
    isDragging = false
  }
}

const handleMouseUp = () => {
  isDragging = false
}

const resumeAutoplay = () => {
  setTimeout(() => {
    carouselRef.value?.start()
  }, 100)
}
</script>

<style scoped>
.carousel-container {
  position: relative;
  width: 100%;
}

.logo-section {
  position: absolute;
  top: 30px;
  left: 40px;
  z-index: 100;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 15px;
  padding: 8px 30px;
  background: rgba(255, 255, 255, 0.15);
  backdrop-filter: blur(10px);
  -webkit-backdrop-filter: blur(10px);
  border-radius: 15px;
  border: 1px solid rgba(255, 255, 255, 0.2);
  box-shadow:
    0 8px 32px 0 rgba(31, 38, 135, 0.15),
    inset 0 0 0 1px rgba(255, 255, 255, 0.1);
  transition: all 0.3s ease;
}

.logo-section.right-section {
  left: auto;
  right: 40px;
}

.logo-section:hover {
  background: rgba(255, 255, 255, 0.95);
  box-shadow:
    0 8px 32px 0 rgba(31, 38, 135, 0.2),
    inset 0 0 0 1px rgba(255, 255, 255, 0.3);
}

.logo {
  width: 50px;
  height: 50px;
  object-fit: contain;
}

.company-name {
  font-size: 28px;
  font-weight: 700;
  color: #ffffff;
  letter-spacing: 2px;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
}

.logo-section:hover .logo-menu :deep(.el-menu-item),
.logo-section:hover .logo-menu :deep(.el-sub-menu__title) {
  color: #333333 !important;
}

.logo-menu {
  background: transparent !important;
  border: none !important;
  margin-top: 17px;
}

:deep(.logo-menu .el-menu-item),
:deep(.logo-menu .el-sub-menu__title) {
  color: #ffffff !important;
  font-size: 16px;
  font-weight: 500;
  height: 50px;
  line-height: 50px;
}

:deep(.logo-menu .el-sub-menu__title:hover) {
  background: rgba(255, 255, 255, 0.1) !important;
}

:deep(.logo-menu .el-menu-item:hover) {
  background: rgba(255, 255, 255, 0.1) !important;
}

:deep(.el-popper.is-light) {
  background: rgba(255, 255, 255, 0.95) !important;
  backdrop-filter: blur(10px);
  border: 1px solid rgba(255, 255, 255, 0.3);
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.15);
  border-radius: 15px !important;
  padding: 10px 0 !important;
}

:deep(.el-popper.is-light .el-menu) {
  background: transparent !important;
}

:deep(.el-popper.is-light .el-menu-item) {
  color: #333333 !important;
  border-radius: 12px;
  margin: 4px 12px;
  padding: 0 20px;
}

:deep(.el-popper.is-light .el-menu-item:hover) {
  background: rgba(64, 158, 255, 0.1) !important;
}

img {
  width: 100%;
  height: auto;
  display: block;
}
.overlay {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.6);
}
.title-container {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
  z-index: 10;
}

.main-title {
  font-size: 180px;
  font-weight: 700;
  line-height: 1.2;
  text-transform: uppercase;
  letter-spacing: 2px;
  color: #ffffff;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
}

.hello-title {
  color: #409EFF;
  margin-bottom: 20px;
}

.world-title {
  margin: 0;
}

</style>
