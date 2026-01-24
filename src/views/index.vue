<template>
  <div class="carousel-container">
    <TopBar class="floating-topbar" />
    <el-carousel
    ref="carouselRef"
       height="850px"
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
          <h1 class="main-title hello-title">Premium</h1>
          <h1 class="main-title world-title">Tanker Trucks for Sale</h1>
        </div>
      </el-carousel-item>
    </el-carousel>
    <div class="feature-boxes" ref="featureBoxesRef">
      <div class="feature-boxes-inner">
        <div class="feature-box feature-bg-1" :class="{ 'animate-in': isFeatureBoxesVisible }" style="animation-delay: 0.1s">
          <h3 class="feature-title">Diverse vehicle models</h3>
          <p class="feature-desc">Adapt to different transportation needs</p>
        </div>
        <div class="feature-box feature-bg-2" :class="{ 'animate-in': isFeatureBoxesVisible }" style="animation-delay: 0.3s">
          <h3 class="feature-title">Compliance and Security</h3>
          <p class="feature-desc">Strict safety standards</p>
        </div>
        <div class="feature-box feature-bg-3" :class="{ 'animate-in': isFeatureBoxesVisible }" style="animation-delay: 0.5s">
          <h3 class="feature-title">Proper and considerate service</h3>
          <p class="feature-desc">Full life cycle service support</p>
        </div>
      </div>
    </div>
    <div class="product-section">
      <div class="product-section-inner">
        <h2 class="section-title">Popular Products</h2>
        <p class="section-subtitle">We Promise To Find You The Right Equipment</p>
        <div class="category-tabs">
          <div
            class="category-tab"
            :class="{ active: currentCategory === 'tanker' }"
            @click="setCategory('tanker')"
          >
            <span class="category-name">Tanker Trucks</span>
          </div>
          <div
            class="category-tab"
            :class="{ active: currentCategory === 'excavator' }"
            @click="setCategory('excavator')"
          >
            <span class="category-name">Excavators</span>
          </div>
        </div>
        <div class="product-grid">
          <div class="product-col" v-for="product in currentProducts" :key="product.id">
            <el-card class="product-card" :body-style="{ padding: '0px', height: '100%' }">
              <div class="product-image">
                <img :src="product.image" class="product-img" />
                <div class="product-tag">{{ product.tag }}</div>
              </div>
              <div class="product-content">
                <h3 class="product-name">{{ product.name }}</h3>
                <div class="product-info">
                  <p class="product-desc">{{ product.desc }}</p>
                  <div class="product-footer">
                    <el-button type="primary" round>View More</el-button>
                  </div>
                </div>
              </div>
            </el-card>
          </div>
        </div>
      </div>
    </div>

    <footer class="footer">
      <div class="footer-content">
        <el-row :gutter="40">
          <el-col :xs="24" :sm="12" :md="6">
            <div class="footer-brand">
              <img src="/public/images/logo.svg" alt="Logo" class="footer-logo">
              <span class="footer-company-name">ZHI SHUN</span>
              <p class="footer-desc">Professional Services</p>
            </div>
          </el-col>
          <el-col :xs="24" :sm="12" :md="6">
            <div class="footer-links">
              <h4 class="footer-title">Quick Links</h4>
              <ul class="link-list">
                <li><a href="#">Home</a></li>
                <li><a href="#">Products</a></li>
                <li><a href="#">Services</a></li>
                <li><a href="#">About Us</a></li>
              </ul>
            </div>
          </el-col>
          <el-col :xs="24" :sm="12" :md="6">
            <div class="footer-links">
              <h4 class="footer-title">TRUCK</h4>
              <ul class="link-list">
                <li><a href="#">Tractor Head Truck</a></li>
                <li><a href="#">Dump Truck</a></li>
                <li><a href="#">Concrete Mixer Truck</a></li>
                <li><a href="#">Other Type Truck</a></li>
              </ul>
            </div>
          </el-col>
          <el-col :xs="24" :sm="12" :md="6">
            <div class="footer-contact">
              <h4 class="footer-title">Contact Us</h4>
              <div class="contact-row">
                <div class="contact-icon i-ep-phone"></div>
                <span>138-0013-8800</span>
              </div>
              <div class="contact-row">
                <div class="contact-icon i-ep-message"></div>
                <span>service@example.com</span>
              </div>
              <div class="contact-row">
                <div class="contact-icon i-ep-location"></div>
                <span>Beijing, China</span>
              </div>
            </div>
          </el-col>
        </el-row>
        <div class="footer-bottom">
          <p class="copyright">© 2026 ZHI SHUN. All rights reserved.</p>
        </div>
      </div>
    </footer>

    <ContactFixed
      phone="138-0013-8800"
      email="service@example.com"
      whatsapp="+1 234-567-8900"
    />

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
  category: 'tanker' | 'excavator'
}

const currentCategory = ref<'tanker' | 'excavator'>('tanker')

const tankerProducts = ref<Product[]>([
  {
    id: 1,
    name: 'Three-axle 40,000L 4-compartment Carbon Steel Tanker Truck',
    desc: 'Efficient filtration for engine health',
    price: 299,
    image: '/public/products/three-axle-40-000l-4-compartment-carbon-steel20cbe.jpg',
    tag: 'Hot',
    category: 'tanker'
  },
  {
    id: 2,
    name: '45000 Liters Oil Tanker Trailer',
    desc: 'The volume of the palm oil tanker semi trailer can be customized to reach 30,000-90,000 liters.',
    price: 599,
    image: '/public/products/45000LitersOilTankerTrailer.jpg',
    tag: 'New',
    category: 'tanker'
  },
    {
    id: 3,
    name: '45000 Liters Oil Tanker Trailer',
    desc: 'The volume of the palm oil tanker semi trailer can be customized to reach 30,000-90,000 liters.',
    price: 599,
    image: '/public/products/45000LitersOilTankerTrailer.jpg',
    tag: 'New',
    category: 'tanker'
  },
    {
    id: 4,
    name: '45000 Liters Oil Tanker Trailer',
    desc: 'The volume of the palm oil tanker semi trailer can be customized to reach 30,000-90,000 liters.',
    price: 599,
    image: '/public/products/45000LitersOilTankerTrailer.jpg',
    tag: 'New',
    category: 'tanker'
  },
    {
    id: 5,
    name: '45000 Liters Oil Tanker Trailer',
    desc: 'The volume of the palm oil tanker semi trailer can be customized to reach 30,000-90,000 liters.',
    price: 599,
    image: '/public/products/45000LitersOilTankerTrailer.jpg',
    tag: 'New',
    category: 'tanker'
  },
  //   {
  //   id: 6,
  //   name: '45000 Liters Oil Tanker Trailer',
  //   desc: 'The volume of the palm oil tanker semi trailer can be customized to reach 30,000-90,000 liters.',
  //   price: 599,
  //   image: '/public/products/45000LitersOilTankerTrailer.jpg',
  //   tag: 'New',
  //   category: 'tanker'
  // },
])

const excavatorProducts = ref<Product[]>([
  {
    id: 101,
    name: 'Hydraulic Excavator XG808',
    desc: 'High performance excavator with advanced hydraulic system',
    price: 899,
    image: '/public/products/excavator-1.jpg',
    tag: 'Hot',
    category: 'excavator'
  },
  {
    id: 102,
    name: 'Mini Excavator XG815',
    desc: 'Compact design for tight spaces and urban construction',
    price: 699,
    image: '/public/products/excavator-2.jpg',
    tag: 'New',
    category: 'excavator'
  },
  {
    id: 103,
    name: 'Large Mining Excavator XG890',
    desc: 'Heavy-duty excavator for mining operations',
    price: 1299,
    image: '/public/products/excavator-3.jpg',
    tag: 'New',
    category: 'excavator'
  },
  {
    id: 104,
    name: 'Wheel Excavator XG820',
    desc: 'Mobile excavator with excellent maneuverability',
    price: 799,
    image: '/public/products/excavator-4.jpg',
    tag: 'New',
    category: 'excavator'
  },
  {
    id: 105,
    name: 'Long Reach Excavator XG825',
    desc: 'Extended reach for deep excavation and demolition',
    price: 999,
    image: '/public/products/excavator-5.jpg',
    tag: 'New',
    category: 'excavator'
  },
  {
    id: 106,
    name: 'Forestry Excavator XG830',
    desc: 'Specialized excavator for forestry applications',
    price: 1099,
    image: '/public/products/excavator-6.jpg',
    tag: 'New',
    category: 'excavator'
  },
])

const currentProducts = computed(() => {
  return currentCategory.value === 'tanker' ? tankerProducts.value : excavatorProducts.value
})

const setCategory = (category: 'tanker' | 'excavator') => {
  currentCategory.value = category
}

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

const featureBoxesRef = ref()
const isFeatureBoxesVisible = ref(false)

onMounted(() => {
  const observer = new IntersectionObserver(
    (entries) => {
      entries.forEach((entry) => {
        if (entry.isIntersecting) {
          isFeatureBoxesVisible.value = true
        }
      })
    },
    { threshold: 0.2 }
  )

  if (featureBoxesRef.value) {
    observer.observe(featureBoxesRef.value)
  }
})
</script>

<style scoped>
.carousel-container {
  position: relative;
  width: 100%;
  user-select: none;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
}

.floating-topbar {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  z-index: 100;
  /* background: rgba(255, 255, 255, 0.1); */
  /* backdrop-filter: blur(10px); */
  /* -webkit-backdrop-filter: blur(10px); */
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
  padding: 0px 30px;
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
  display: flex;
  justify-content: center;
  align-items: center;
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
  top: 45%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
  z-index: 10;
  animation: slideUp 0.8s ease-out forwards;
}

.main-title {
  font-size: 170px;
  font-weight: 700;
  line-height: 1.2;
  text-transform: uppercase;
  letter-spacing: 2px;
  color: #ffffff;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
  opacity: 0;
  transform: translateY(50px);
}

.hello-title {
  color: #409EFF;
  margin-bottom: 20px;
  animation: fadeInUp 0.8s ease-out 0.2s forwards;
}

.world-title {
  margin: 0;
   font-size: 100px;
  animation: fadeInUp 0.8s ease-out 0.5s forwards;
}

@keyframes slideUp {
  from {
    opacity: 0;
    transform: translate(-50%, -40%);
  }
  to {
    opacity: 1;
    transform: translate(-50%, -50%);
  }
}

@keyframes fadeInUp {
  from {
    opacity: 0;
    transform: translateY(50px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.feature-boxes {
  display: flex;
  padding: 60px 0;
  background: #000;
  overflow: hidden;
  justify-content: center;
}

.feature-boxes-inner {
  display: flex;
  gap: 40px;
  max-width: 1800px;
  width: 100%;
  padding: 0 40px;
  box-sizing: border-box;
}

.feature-box {
  flex: 1;
  max-width: none;
  padding: 80px 70px;
  background: #16213e;
  border-radius: 20px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.3);
  text-align: center;
  transition: all 0.3s ease;
  border: 1px solid #2a3a5c;
  background-size: cover;
  background-position: center;
  position: relative;
  min-height: 160px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  opacity: 0;
  transform: translateY(100px) scale(0.9);
  transition: opacity 0.8s cubic-bezier(0.34, 1.56, 0.64, 1), transform 0.8s cubic-bezier(0.34, 1.56, 0.64, 1);
}

.feature-box.animate-in {
  opacity: 1;
  transform: translateY(0) scale(1);
}

.feature-box:hover {
  transform: translateY(-5px) scale(1.02);
  box-shadow: 0 15px 40px rgba(64, 158, 255, 0.25);
  border-color: #409EFF;
}

@media (max-width: 992px) {
  .feature-boxes {
    flex-direction: column;
    gap: 20px;
  }

  .feature-box {
    width: 100%;
    padding: 40px 25px;
  }
}

@media (max-width: 768px) {
  .feature-box {
    padding: 30px 20px;
  }
}

.feature-box::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(22, 33, 62, 0.85);
  border-radius: 20px;
  z-index: 1;
}

.feature-box > * {
  position: relative;
  z-index: 2;
}

.feature-bg-1 {
  background-image: url('/feature/feature1.png');
}

.feature-bg-2 {
  background-image: url('/feature/feature2.png');
}

.feature-bg-3 {
  background-image: url('/feature/feature3.png');
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
  color: #fff;
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
  padding: 60px 0;
  background: #000;
  width: 100%;
  display: flex;
  justify-content: center;
}

.product-section-inner {
  width: 100%;
  max-width: 1400px;
  padding: 0 40px;
  box-sizing: border-box;
}

.category-tabs {
  display: flex;
  justify-content: center;
  gap: 20px;
  margin-bottom: 40px;
}

.category-tab {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 16px 32px;
  background: rgba(22, 33, 62, 0.8);
  border: 2px solid #2a3a5c;
  border-radius: 16px;
  cursor: pointer;
  transition: all 0.3s ease;
}

.category-tab:hover {
  background: rgba(64, 158, 255, 0.15);
  border-color: #409EFF;
  transform: translateY(-3px);
}

.category-tab.active {
  background: rgba(64, 158, 255, 0.2);
  border-color: #409EFF;
  box-shadow: 0 4px 20px rgba(64, 158, 255, 0.3);
}

.category-icon {
  font-size: 28px;
}

.category-name {
  font-size: 18px;
  font-weight: 600;
  color: #ffffff;
}

@media (max-width: 768px) {
  .category-tabs {
    flex-direction: column;
    align-items: center;
    gap: 15px;
  }

  .category-tab {
    width: 100%;
    max-width: 280px;
    justify-content: center;
    padding: 14px 24px;
  }

  .category-icon {
    font-size: 24px;
  }

  .category-name {
    font-size: 16px;
  }
}

.section-title {
  font-size: 32px;
  font-weight: 700;
  color: #ffffff;
  text-align: center;
  margin: 0 0 10px 0;
}

.section-subtitle {
  font-size: 16px;
  font-weight: 400;
  color: #8892b0;
  text-align: center;
  margin: 0 0 40px 0;
}

.product-grid {
  max-width: 1400px;
  margin: 0 auto;
  display: flex;
  flex-wrap: wrap;
  gap: 24px;
  justify-content: flex-start;
}

.product-col {
  width: calc(33.333% - 16px);
}

@media (max-width: 1200px) {
  .product-col {
    width: calc(33.333% - 16px);
  }
}

@media (max-width: 992px) {
  .product-col {
    width: calc(50% - 12px);
  }
}

@media (max-width: 768px) {
  .product-col {
    width: calc(50% - 12px);
  }
}

@media (max-width: 480px) {
  .product-col {
    width: 100%;
  }
}

.product-card {
  background: #16213e;
  border: 1px solid #2a3a5c;
  border-radius: 16px;
  overflow: hidden;
  transition: all 0.3s ease;
  height: 285px;
}

.product-card:hover {
  transform: translateY(-8px);
  box-shadow: 0 12px 40px rgba(64, 158, 255, 0.2);
  border-color: #409EFF;
}

.product-image {
  position: relative;
  width: 95%;
  height: 170px;
  margin: 16px auto 0;
  padding: 3px;
  background: linear-gradient(135deg, #0f1626 0%, #1a2744 100%);
  overflow: hidden;
}

.product-layout {
  display: block;
  height: 100%;
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
  padding: 12px 16px;
  flex-grow: 1;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  overflow: hidden;
}

.product-name {
  font-size: 16px;
  font-weight: 600;
  color: #ffffff;
  margin: 0 0 8px 0;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.product-info {
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 12px;
}

.product-desc {
  font-size: 13px;
  color: #8892b0;
  margin: 0;
  line-height: 1.5;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  flex: 1;
}

.product-footer {
  display: flex;
  justify-content: flex-start;
  align-items: center;
}

.product-footer .el-button {
  font-size: 14px;
  padding: 8px 20px;
}

.product-price {
  display: none;
}

.footer {
  background: #000;
  padding: 60px 40px 30px;
  border-top: 1px solid #2a3a5c;
}

.footer-content {
  max-width: 1400px;
  margin: 0 auto;
}

.footer-brand {
  padding-right: 20px;
}

.footer-logo {
  width: 50px;
  height: 50px;
  object-fit: contain;
  margin-bottom: 15px;
}

.footer-company-name {
  font-size: 24px;
  font-weight: 700;
  color: #ffffff;
  display: block;
  margin-bottom: 15px;
}

.footer-desc {
  font-size: 14px;
  color: #8892b0;
  margin: 0;
  line-height: 1.7;
}

.footer-title {
  font-size: 18px;
  font-weight: 600;
  color: #ffffff;
  margin: 0 0 20px 0;
  position: relative;
  padding-bottom: 12px;
}

.footer-title::after {
  content: '';
  position: absolute;
  bottom: 0;
  left: 0;
  width: 40px;
  height: 3px;
  background: linear-gradient(90deg, #409EFF, transparent);
  border-radius: 2px;
}

.link-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.link-list li {
  margin-bottom: 12px;
}

.link-list a {
  color: #8892b0;
  text-decoration: none;
  font-size: 14px;
  transition: all 0.3s ease;
  display: inline-block;
  position: relative;
}

.link-list a::before {
  content: '›';
  margin-right: 8px;
  color: #409EFF;
  opacity: 0;
  transform: translateX(-10px);
  transition: all 0.3s ease;
  display: inline-block;
}

.link-list a:hover {
  color: #409EFF;
  transform: translateX(5px);
}

.link-list a:hover::before {
  opacity: 1;
  transform: translateX(0);
}

.footer-contact {
  padding-left: 10px;
}

.contact-row {
  display: flex;
  align-items: center;
  gap: 12px;
  margin-bottom: 15px;
  color: #8892b0;
  font-size: 14px;
}

.contact-icon {
  font-size: 18px;
  color: #ffffff;
  width: 32px;
  height: 32px;
  display: flex;
  align-items: center;
  justify-content: center;
  /* background: rgba(64, 158, 255, 0.1); */
  border-radius: 10px;
}

.footer-bottom {
  margin-top: 50px;
  padding-top: 25px;
  border-top: 1px solid #2a3a5c;
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-wrap: wrap;
  gap: 20px;
}

.copyright {
  font-size: 14px;
  color: #8892b0;
  margin: 0;
}

.social-links {
  display: flex;
  gap: 15px;
}

.social-link {
  width: 40px;
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: rgba(64, 158, 255, 0.1);
  border-radius: 12px;
  color: #8892b0;
  font-size: 20px;
  text-decoration: none;
  transition: all 0.3s ease;
}

.social-link:hover {
  background: #409EFF;
  color: #ffffff;
  transform: translateY(-3px);
  box-shadow: 0 4px 15px rgba(64, 158, 255, 0.4);
}

@media (max-width: 768px) {
  .footer {
    padding: 40px 20px 25px;
  }

  .footer-bottom {
    flex-direction: column;
    text-align: center;
    margin-top: 35px;
    padding-top: 20px;
  }

  .footer-brand {
    margin-bottom: 30px;
    padding-right: 0;
  }

  .footer-contact {
    padding-left: 0;
  }
}

</style>
