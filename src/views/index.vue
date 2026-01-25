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
          <template v-if="item === 1">
            <h1 class="main-title hello-title">Powerful</h1>
            <h1 class="main-title world-title">Excavators for Sale</h1>
          </template>
          <template v-else>
            <h1 class="main-title hello-title">Premium</h1>
            <h1 class="main-title world-title">Tanker Trucks for Sale</h1>
          </template>
        </div>
      </el-carousel-item>
    </el-carousel>
    <div class="feature-boxes" ref="featureBoxesRef">
      <div class="feature-section-header">
        <h2 class="section-title">Our Advantages</h2>
        <p class="section-subtitle">Why Leading Companies Choose Our Solutions</p>
      </div>
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

    <div class="features-section">
      <div class="features-container">
        <h2 class="section-title">Why Choose Us</h2>
        <p class="section-subtitle">What sets us apart from the competition</p>
        <div class="features-grid">
          <div class="feature-card">
            <div class="feature-icon">
              <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor">
                <path d="M12 2L2 7l10 5 10-5-10-5zM2 17l10 5 10-5M2 12l10 5 10-5"/>
              </svg>
            </div>
            <h3 class="feature-title">Quality First</h3>
            <p class="feature-desc">Officially certified by China’s MIIT, guaranteeing industry-leading quality control</p>
          </div>
          <div class="feature-card">
            <div class="feature-icon">
              <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor">
                <path d="M12 22s8-4 8-10V5l-8-3-8 3v7c0 6 8 10 8 10z"/>
              </svg>
            </div>
            <h3 class="feature-title">Safety Certified</h3>
            <p class="feature-desc">All products meet international safety standards and certifications</p>
          </div>
          <div class="feature-card">
            <div class="feature-icon">
              <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor">
                <circle cx="12" cy="12" r="10"/>
                <path d="M12 6v6l4 2"/>
              </svg>
            </div>
            <h3 class="feature-title">On-Time Delivery</h3>
            <p class="feature-desc">Efficient logistics network ensuring timely delivery worldwide</p>
          </div>
          <div class="feature-card">
            <div class="feature-icon">
              <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor">
                <path d="M21 15a2 2 0 0 1-2 2H7l-4 4V5a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2z"/>
              </svg>
            </div>
            <h3 class="feature-title">24/7 Support</h3>
            <p class="feature-desc">Dedicated customer service team available around the clock</p>
          </div>
        </div>
      </div>
    </div>

    <div class="why-choose-section">
        <div class="why-choose-container">
          <div class="why-choose-left">
            <h2 class="section-title">ABOUT US</h2>
            <p class="why-intro">We also provide customized services for customers. According to different transportation needs, we can customize the cargo size, carrying capacity, tank body materials, loading and unloading equipment, etc. of tank semi-trailers to ensure that customers can obtain the most suitable solutions for their businesses.</p>
            <div class="why-stats">
              <div class="stat-item">
                <div class="stat-number">5,000+</div>
                <div class="stat-label">Annual Sales</div>
              </div>
              <div class="stat-item">
                <div class="stat-number">25%+</div>
                <div class="stat-label">Growth Rate</div>
              </div>
              <div class="stat-item">
                <div class="stat-number">80+</div>
                <div class="stat-label">Countries & Regions</div>
              </div>
              <div class="stat-item">
                <div class="stat-number">20+</div>
                <div class="stat-label">Years Experience</div>
              </div>
            </div>
          </div>
          <div class="why-choose-right">
            <div class="video-container">
              <video autoplay muted loop playsinline class="promo-video">
                <source src="/videos/promo.mp4" type="video/mp4">
              </video>
              <!-- <div class="video-overlay">
                <div class="play-button">▶</div>
              </div> -->
            </div>
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
            <el-card class="product-card" :body-style="{ padding: '0px', height: '100%' }"  :style="{ cursor: 'pointer' }">
              <div class="product-image">
                <img :src="product.image" class="product-img" />
                <div class="product-tag">{{ product.tag }}</div>
              </div>
              <div class="product-content">
                <h3 class="product-name">{{ product.name }}</h3>
                <div class="product-info">
                  <p class="product-desc">{{ product.desc }}</p>
                  <div class="product-footer">
                    <el-button type="primary" round @click="goToProduct(product.id)">View More</el-button>
                  </div>
                </div>
              </div>
            </el-card>
          </div>
        </div>
      </div>
    </div>

    <div class="cta-section">
      <div class="cta-content">
        <h2 class="cta-title">IF YOU HAVE ANY ENQUIRY ABOUT QUOTATION OR COOPERATION, PLEASE FEEL FREE TO EMAIL US</h2>
        <el-button type="primary" round class="cta-button" @click="openContactDialog">Contact Us</el-button>
      </div>
    </div>

    <Footer
      phone="138-0013-8800"
      email="service@example.com"
      whatsapp="+1 234-567-8900"
      address="Beijing, China"
    />

    <ContactFixed
      phone="138-0013-8800"
      email="service@example.com"
      whatsapp="+1 234-567-8900"
    />

    <el-dialog v-model="contactDialogVisible" title="Contact Us" width="500px" :close-on-click-modal="false" class="contact-dialog">
      <el-form :model="contactForm" label-position="top" class="contact-form">
        <el-form-item label="Name" required>
          <el-input v-model="contactForm.name" placeholder="Your name" />
        </el-form-item>
        <el-form-item label="Email" required>
          <el-input v-model="contactForm.email" placeholder="Your email" />
        </el-form-item>
        <el-form-item label="Phone">
          <el-input v-model="contactForm.phone" placeholder="Your phone number" />
        </el-form-item>
        <el-form-item label="Message" required>
          <el-input v-model="contactForm.message" type="textarea" :rows="4" placeholder="Your message" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="contactDialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="submitContactForm">Submit</el-button>
      </template>
    </el-dialog>

  </div>
</template>

<script lang="ts">
const loadCarouselImages = async () => {
  const modules = import.meta.glob('/public/images/carousel/*.{png,jpg,jpeg,gif,svg,webp}');
  const files = await Promise.all(Object.keys(modules));
  return files.length;
};
</script>

<script setup lang="ts">
import TopBar from '@/components/TopBar.vue'
import Footer from '@/components/Footer.vue'
import ContactFixed from '@/components/ContactFixed.vue'
import { useRouter } from 'vue-router'
import { getProductsByCategory, getProductById } from '@/data/products'

const router = useRouter()

const imageCount = ref(0);

onMounted(async () => {
  imageCount.value = await loadCarouselImages();
});

const currentCategory = ref<'tanker' | 'excavator'>('tanker')

const currentProducts = computed(() => {
  return getProductsByCategory(currentCategory.value)
})

function setCategory(category: 'tanker' | 'excavator') {
  currentCategory.value = category
}

function goToProduct(id: number) {
  const product = getProductById(id)
  if (product) {
    if (product.category === 'tanker') {
      router.push(`/truck/${id}`)
    } else if (product.category === 'excavator') {
      router.push(`/excavator/${id}`)
    }
  }
}

const contactDialogVisible = ref(false)

const contactForm = reactive({
  name: '',
  email: '',
  phone: '',
  message: ''
})

function openContactDialog() {
  contactDialogVisible.value = true
}

function submitContactForm() {
  if (!contactForm.name || !contactForm.email || !contactForm.message) {
    return
  }
  console.log('Form submitted:', contactForm)
  contactDialogVisible.value = false
  Object.assign(contactForm, { name: '', email: '', phone: '', message: '' })
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
  /* background: rgba(255, 255, 255, 0.1);
  backdrop-filter: blur(10px);
  -webkit-backdrop-filter: blur(10px); */
}
/* .floating-topbar {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 1000;
} */
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
  background: rgba(0, 0, 0, 0.55);
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
  color: #8B0000;
  margin-bottom: 20px;
  animation: fadeInUp 0.8s ease-out 0.2s forwards;
}

.world-title {
  /* color: #FF0000; */
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
  flex-direction: column;
  padding: 60px 0;
  background: #F6F5ED;
  overflow: hidden;
  align-items: center;
}

.feature-section-header {
  text-align: center;
  margin-bottom: 40px;
}

.feature-section-header .section-subtitle {
  font-size: 16px;
  color: #666666;
  margin: 10px 0 0 0;
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
  background: linear-gradient(145deg, #ffffff 0%, #f8f7f2 100%);
  border-radius: 20px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
  text-align: center;
  transition: all 0.3s ease;
  border: 1px solid #e8e4dd;
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
  box-shadow: 0 15px 40px rgba(52, 152, 219, 0.12);
  border-color: #FF0000;
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
  background: rgba(255, 255, 255, 0.7);
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
  box-shadow: 0 8px 30px rgba(52, 152, 219, 0.12);
}

.feature-icon {
  font-size: 48px;
  color: #FF0000;
  margin-bottom: 20px;
  filter: drop-shadow(0 2px 4px rgba(52, 152, 219, 0.3));
}

.feature-title {
  font-size: 22px;
  font-weight: 700;
  color: #1a2a4a;
  margin: 0 0 12px 0;
  letter-spacing: 0.5px;
  text-shadow: 0 1px 2px rgba(0, 0, 0, 0.05);
}

.feature-desc {
  font-size: 15px;
  color: #4a5568;
  margin: 0;
  line-height: 1.7;
  font-weight: 400;
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
  background: #fff;
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
  background: #ffffff;
  border: 2px solid #e2e8f0;
  border-radius: 16px;
  cursor: pointer;
  transition: all 0.3s ease;
}

.category-tab:hover {
  background: #FF0000;
  border-color: #8B0000;
  transform: translateY(-3px);
}

.category-tab.active {
  background: #FF0000;
  border-color: #8B0000;
  box-shadow: 0 4px 20px rgba(64, 158, 255, 0.3);
}

.category-name {
  font-size: 18px;
  font-weight: 600;
  color: #1a2a4a;
}

.category-tab:hover .category-name,
.category-tab.active .category-name {
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
  color: #1a2a4a;
  text-align: center;
  margin: 0 0 10px 0;
}

.section-subtitle {
  font-size: 16px;
  font-weight: 400;
  color: #4a5568;
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
  background: #ffffff;
  border: 1px solid #e0ddd5;
  border-radius: 16px;
  overflow: hidden;
  transition: all 0.3s ease;
  height: 285px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.06);
}

.product-card:hover {
  transform: translateY(-8px);
  box-shadow: 0 12px 40px rgba(52, 152, 219, 0.15);
  border-color: #FF0000;
}

.product-image {
  position: relative;
  width: 95%;
  height: 170px;
  margin: 16px auto 0;
  padding: 3px;
  background: linear-gradient(135deg, #f0f4f8 0%, #e8eef4 100%);
  overflow: hidden;
  border-radius: 12px;
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
  background: #FF0000;
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
  color: #2c3e50;
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
  color: #7a8a9a;
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
  font-size: 13px;
  padding: 8px 18px;
  border-radius: 20px;
  background: #FF0000;
  border: none;
  font-weight: 500;
  transition: all 0.3s ease;
}

.product-footer .el-button:hover {
  background: #B22222;
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(52, 152, 219, 0.35);
}

.product-price {
  display: none;
}

.features-section {
  padding: 100px 40px;
  background: #F6F5ED;
}

.features-container {
  max-width: 1400px;
  margin: 0 auto;
}

.features-grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 30px;
}

@media (max-width: 1200px) {
  .features-grid {
    grid-template-columns: repeat(2, 1fr);
  }
}

@media (max-width: 768px) {
  .features-grid {
    grid-template-columns: 1fr;
  }
}

.feature-card {
  background: #ffffff;
  border: 1px solid #e2e8f0;
  border-radius: 16px;
  padding: 30px;
  text-align: center;
  transition: all 0.3s ease;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.06);
}

.feature-card:hover {
  transform: translateY(-8px);
  border-color: #FF0000;
  box-shadow: 0 12px 40px rgba(255, 0, 0, 0.15);
}

.feature-icon {
  width: 60px;
  height: 60px;
  margin: 0 auto 20px;
  color: #FF0000;
}

.feature-icon svg {
  width: 100%;
  height: 100%;
}

.feature-title {
  font-size: 20px;
  font-weight: 600;
  color: #1a2a4a;
  margin: 0 0 12px 0;
}

.feature-desc {
  font-size: 14px;
  color: #4a5568;
  line-height: 1.6;
  margin: 0;
}

.why-choose-section {
  background: linear-gradient(180deg, #ffffff 0%, #F6F5ED 100%);
  padding: 80px 20px;
}

.why-choose-container {
  max-width: 1400px;
  margin: 0 auto;
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 60px;
  align-items: center;
}

@media (max-width: 992px) {
  .why-choose-container {
    grid-template-columns: 1fr;
    gap: 40px;
  }
}

.why-choose-left {
  padding-right: 20px;
}

@media (max-width: 992px) {
  .why-choose-left {
    padding-right: 0;
    order: 2;
  }
  .why-choose-right {
    order: 1;
  }
}

.why-intro {
  font-size: 16px;
  line-height: 1.8;
  color: #555555;
  margin: 30px 0;
}

.why-stats {
  display: flex;
  gap: 20px;
  margin-top: 40px;
}

@media (max-width: 992px) {
  .why-stats {
    flex-wrap: wrap;
    justify-content: center;
    gap: 20px;
  }
}

@media (max-width: 768px) {
  .why-stats {
    flex-direction: row;
    gap: 16px;
  }
}

.stat-item {
  text-align: center;
}

.stat-number {
  font-size: 42px;
  font-weight: 700;
  color: #FF0000;
  line-height: 1.2;
}

.stat-label {
  font-size: 14px;
  color: #666666;
  margin-top: 8px;
  text-transform: uppercase;
  letter-spacing: 1px;
}

.why-choose-right {
  display: flex;
  justify-content: center;
  align-items: center;
}

.video-container {
  position: relative;
  width: 100%;
  max-width: 560px;
  aspect-ratio: 16 / 9;
  border-radius: 20px;
  overflow: hidden;
  box-shadow: 0 20px 60px rgba(0, 0, 0, 0.15);
}

.promo-video {
  width: 100%;
  height: 100%;
  object-fit: cover;
  background: #1a2a4a;
}

.video-overlay {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.3);
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  transition: background 0.3s ease;
}

.video-container:hover .video-overlay {
  background: rgba(0, 0, 0, 0.2);
}

.play-button {
  width: 80px;
  height: 80px;
  background: rgba(255, 255, 255, 0.9);
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 28px;
  color: #FF0000;
  transition: transform 0.3s ease, background 0.3s ease;
}

.video-container:hover .play-button {
  transform: scale(1.1);
  background: #ffffff;
}

.cta-section {
  background: #F6F5ED;
  padding: 40px 20px;
  text-align: center;
}

.cta-content {
  max-width: 1200px;
  margin: 0 auto;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 40px;
  flex-wrap: wrap;
}

.cta-title {
  font-size: 20px;
  font-weight: 600;
  color: #1a2a4a;
  margin: 0;
  line-height: 1.4;
  flex: 1;
  min-width: 300px;
  text-align: left;
}

@media (max-width: 768px) {
  .cta-title {
    font-size: 16px;
    text-align: center;
    min-width: 100%;
  }
  .cta-content {
    flex-direction: column;
    gap: 20px;
  }
}

.cta-button {
  font-size: 16px;
  padding: 12px 36px;
  background: #FF0000;
  border: none;
  font-weight: 600;
  white-space: nowrap;
  transition: all 0.3s ease;
}

.cta-button:hover {
  background: #B22222;
  transform: translateY(-2px);
  box-shadow: 0 4px 15px rgba(255, 0, 0, 0.35);
}

.contact-dialog .el-dialog {
  border-radius: 16px;
  background: rgba(255, 0, 0, 0.03);
  border: 2px solid rgba(255, 0, 0, 0.15);
}

.contact-dialog .el-dialog__header {
  text-align: center;
  padding-bottom: 0;
  border-bottom: 1px solid rgba(255, 0, 0, 0.1);
}

.contact-dialog .el-dialog__title {
  font-size: 24px;
  font-weight: 600;
  color: #FF0000;
}

.contact-dialog .el-dialog__headerbtn {
  top: 12px;
  right: 12px;
}

.contact-dialog .el-dialog__headerbtn .el-dialog__close {
  color: #FF0000;
  font-size: 20px;
}

.contact-dialog .el-dialog__headerbtn:hover .el-dialog__close {
  color: #B22222;
}

.contact-dialog .el-dialog__body {
  padding: 24px;
}

.contact-dialog .el-dialog__footer {
  border-top: 1px solid rgba(255, 0, 0, 0.1);
  padding: 16px 24px;
}

.contact-form .el-form-item__label {
  font-weight: 500;
  color: #FF0000;
}

.contact-form .el-input__wrapper,
.contact-form .el-textarea__inner {
  border-radius: 8px;
  background: #ffffff;
  border: 2px solid rgba(255, 0, 0, 0.2);
  box-shadow: none;
  transition: all 0.3s ease;
}

.contact-form .el-input__wrapper:hover,
.contact-form .el-textarea__inner:hover {
  border-color: rgba(255, 0, 0, 0.4);
}

.contact-form .el-input__wrapper.is-focus,
.contact-form .el-textarea__inner:focus {
  border-color: #FF0000;
  background: #ffffff;
}

.contact-form .el-input__inner {
  color: #1a2a4a;
}

.contact-form .el-input__inner::placeholder {
  color: #8892b0;
}

.contact-form .el-textarea__inner {
  color: #1a2a4a;
  resize: none;
}

.contact-form .el-textarea__inner::placeholder {
  color: #8892b0;
}

.contact-dialog :deep(.el-dialog__footer .el-button) {
  padding: 12px 32px;
  border-radius: 8px;
  font-weight: 600;
  transition: all 0.3s ease;
}

.contact-dialog :deep(.el-dialog__footer .el-button:not(.el-button--primary):not(.el-button--danger)) {
  background: transparent !important;
  border: 2px solid #FF0000 !important;
  color: #FF0000 !important;
}

.contact-dialog :deep(.el-dialog__footer .el-button:not(.el-button--primary):not(.el-button--danger):hover) {
  background: rgba(255, 0, 0, 0.05) !important;
  border-color: #FF0000 !important;
  color: #FF0000 !important;
}

.contact-dialog :deep(.el-dialog__footer .el-button--danger) {
  background: #FF0000 !important;
  border: 2px solid #FF0000 !important;
  color: #ffffff !important;
}

.contact-dialog :deep(.el-dialog__footer .el-button--danger:hover) {
  background: #B22222 !important;
  border-color: #B22222 !important;
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(255, 0, 0, 0.3);
}

.contact-dialog :deep(.el-dialog__footer .el-button:active) {
  transform: translateY(0);
}
</style>
