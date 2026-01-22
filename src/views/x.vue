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
    <div class="feature-boxes">
      <div class="feature-box">
        <div class="feature-icon i-ep-service"></div>
        <h3 class="feature-title">维修保养</h3>
        <p class="feature-desc">专业维修团队</p>
      </div>
      <div class="feature-box">
        <div class="feature-icon i-ep-setting"></div>
        <h3 class="feature-title">零部件更换</h3>
        <p class="feature-desc">原厂品质保障</p>
      </div>
      <div class="feature-box">
        <div class="feature-icon i-ep-search"></div>
        <h3 class="feature-title">检测服务</h3>
        <p class="feature-desc">全面专业检测</p>
      </div>
    </div>
    <div class="product-section">
      <h2 class="section-title">热门产品</h2>
      <el-row :gutter="24" class="product-grid">
        <el-col :xs="24" :sm="12" :md="8" :lg="6" v-for="product in products" :key="product.id">
          <el-card class="product-card" :body-style="{ padding: '0px' }">
            <div class="product-image">
              <img :src="product.image" class="product-img" />
              <div class="product-tag">{{ product.tag }}</div>
            </div>
            <div class="product-content">
              <h3 class="product-name">{{ product.name }}</h3>
              <p class="product-desc">{{ product.desc }}</p>
              <div class="product-footer">
                <span class="product-price">¥{{ product.price }}</span>
                <el-button type="primary" size="small" round>查看详情</el-button>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
        <ContactFixed
      phone="138-0013-8800"
      email="service@example.com"
      whatsapp="+1 234-567-8900"
    />
    <!-- <div class="floating-contact">
      <div class="contact-item">
        <div class="contact-icon i-ep-phone"></div>
        <div class="contact-info">
          <span class="contact-label">电话</span>
          <span class="contact-value">400-123-4567</span>
        </div>
      </div>
      <div class="contact-item">
        <div class="contact-icon i-ep-message"></div>
        <div class="contact-info">
          <span class="contact-label">邮件</span>
          <span class="contact-value">contact@example.com</span>
        </div>
      </div>
    </div> -->
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

interface Product {
  id: number
  name: string
  desc: string
  price: number
  image: string
  tag: string
}

const products = ref<Product[]>([
  {
    id: 1,
    name: '高端机油滤清器',
    desc: '高效过滤，保障发动机健康',
    price: 299,
    image: '/images/products/oil-filter.png',
    tag: '热销'
  },
  {
    id: 2,
    name: '刹车片套装',
    desc: '陶瓷配方，制动性能卓越',
    price: 599,
    image: '/images/products/brake-pad.png',
    tag: '新品'
  },
  {
    id: 3,
    name: '空气滤清器',
    desc: '高效过滤，清洁进气',
    price: 149,
    image: '/images/products/air-filter.png',
    tag: ''
  },
  {
    id: 4,
    name: '火花塞套装',
    desc: '铱金材质，点火更强劲',
    price: 399,
    image: '/images/products/spark-plug.png',
    tag: '推荐'
  }
])

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
  height: 100%;
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

.feature-boxes {
  display: flex;
  justify-content: center;
  gap: 30px;
  padding: 60px 40px;
  background: #1a1a2e;
}

.feature-box {
  flex: 1;
  max-width: 400px;
  padding: 40px 30px;
  background: #16213e;
  border-radius: 20px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.3);
  text-align: center;
  transition: all 0.3s ease;
  border: 1px solid #2a3a5c;
}

.feature-box:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 30px rgba(64, 158, 255, 0.15);
  border-color: #409EFF;
}

.feature-icon {
  font-size: 48px;
  color: #409EFF;
  margin-bottom: 20px;
}

.feature-title {
  font-size: 22px;
  font-weight: 600;
  color: #ffffff;
  margin: 0 0 12px 0;
}

.feature-desc {
  font-size: 15px;
  color: #8892b0;
  margin: 0;
  line-height: 1.6;
}

.floating-contact {
  position: fixed;
  right: 40px;
  bottom: 40px;
  z-index: 1000;
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.contact-item {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 15px 20px;
  background: rgba(22, 33, 62, 0.9);
  backdrop-filter: blur(10px);
  border-radius: 15px;
  border: 1px solid #2a3a5c;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.3);
  cursor: pointer;
  transition: all 0.3s ease;
}

.contact-item:hover {
  background: rgba(64, 158, 255, 0.2);
  border-color: #409EFF;
  transform: translateX(-5px);
}

.contact-icon {
  font-size: 24px;
  color: #409EFF;
}

.contact-info {
  display: flex;
  flex-direction: column;
}

.contact-label {
  font-size: 12px;
  color: #8892b0;
  margin-bottom: 2px;
}

.contact-value {
  font-size: 14px;
  color: #ffffff;
  font-weight: 500;
}

.product-section {
  padding: 60px 40px;
  background: #1a1a2e;
}

.section-title {
  font-size: 32px;
  font-weight: 700;
  color: #ffffff;
  text-align: center;
  margin: 0 0 40px 0;
}

.product-grid {
  max-width: 1400px;
  margin: 0 auto;
}

.product-card {
  background: #16213e;
  border: 1px solid #2a3a5c;
  border-radius: 16px;
  overflow: hidden;
  transition: all 0.3s ease;
  margin-bottom: 20px;
}

.product-card:hover {
  transform: translateY(-8px);
  box-shadow: 0 12px 40px rgba(64, 158, 255, 0.2);
  border-color: #409EFF;
}

.product-image {
  position: relative;
  height: 200px;
  background: linear-gradient(135deg, #0f1626 0%, #1a2744 100%);
  overflow: hidden;
}

.product-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.product-tag {
  position: absolute;
  top: 12px;
  left: 12px;
  padding: 4px 12px;
  background: #409EFF;
  color: #ffffff;
  font-size: 12px;
  font-weight: 500;
  border-radius: 20px;
}

.product-content {
  padding: 20px;
}

.product-name {
  font-size: 18px;
  font-weight: 600;
  color: #ffffff;
  margin: 0 0 8px 0;
}

.product-desc {
  font-size: 14px;
  color: #8892b0;
  margin: 0 0 16px 0;
  line-height: 1.5;
}

.product-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.product-price {
  font-size: 24px;
  font-weight: 700;
  color: #409EFF;
}

</style>
