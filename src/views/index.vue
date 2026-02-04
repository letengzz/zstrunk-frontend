<template>
  <div class="carousel-container">
    <TopBar class="floating-topbar" />
    <el-carousel
    ref="carouselRef"
    height="calc(100vh)"
    indicator-position="none"
      arrow="never"
      :autoplay="true"
      :interval="6000"
      :pause-on-hover="false"
      @mousedown="handleMouseDown"
      @mousemove="handleMouseMove"
      @mouseup="handleMouseUp"
      @mouseleave="handleMouseUp"
      @click="resumeAutoplay"
       >
      <el-carousel-item v-for="item in imageCount" :key="item">
        <div class="carousel-image-wrapper">
          <img :src="`/images/carousel/carousel${item}.png`" class="carousel-img" alt="">
        </div>
        <div class="overlay"></div>
        <div class="title-container">
          <template v-if="carouselItems[item - 1]">
            <h1 class="main-title hello-title">{{ carouselItems[item - 1]?.mainTitle }}</h1>
            <h1 class="main-title world-title">{{ carouselItems[item - 1]?.subTitle }}</h1>
            <router-link to="/products" class="carousel-button">View More</router-link>
          </template>
        </div>
      </el-carousel-item>
    </el-carousel>
    <div class="why-choose-section" ref="whyChooseRef">
        <div class="why-choose-container">
          <div class="why-choose-left">
            <h2 class="section-title">ABOUT US</h2>
            <p class="about-subtitle">Trusted Manufacturer of High-Quality Flatbed, Fuel Tanker, and Specialized Trailers for Global Markets</p>
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
            <div class="extra-stats">
              <div class="stat-simple-item">
                <div class="stat-simple-icon">
                  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor">
                    <path d="M12 7V3H2v18h20V7H12zM6 19H4v-2h2v2zm0-4H4v-2h2v2zm0-4H4V9h2v2zm0-4H4V5h2v2zm4 12H8v-2h2v2zm0-4H8v-2h2v2zm0-4H8V9h2v2zm0-4H8V5h2v2zm10 12h-8v-2h2v-2h-2v-2h2v-2h-2V9h8v10zm-2-8h-2v2h2v-2zm0 4h-2v2h2v-2z"/>
                  </svg>
                </div>
                <div class="stat-simple-content">
                  <div class="stat-simple-number">{{ animatedStats.exhibitionHall }}㎡</div>
                  <div class="stat-simple-label">Exhibition Hall</div>
                  <div class="stat-simple-line"></div>
                </div>
              </div>
              <div class="stat-simple-item">
                <div class="stat-simple-icon">
                  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor">
                    <path d="M22 9V7h-2V5c0-1.1-.9-2-2-2H4c-1.1 0-2 .9-2 2v14c0 1.1.9 2 2 2h14c1.1 0 2-.9 2-2v-2h2v-2h-2v-2h2v-2h-2V9h2zm-4 10H4V5h14v14zM6 13h5v4H6zm6-6h4v3h-4zM6 7h5v5H6zm6 4h4v6h-4z"/>
                  </svg>
                </div>
                <div class="stat-simple-content">
                  <div class="stat-simple-number">{{ animatedStats.productionArea }}㎡</div>
                  <div class="stat-simple-label">Total Area Of The Production Plant</div>
                  <div class="stat-simple-line"></div>
                </div>
              </div>
              <div class="stat-simple-item">
                <div class="stat-simple-icon">
                  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor">
                    <path d="M16 11c1.66 0 2.99-1.34 2.99-3S17.66 5 16 5c-1.66 0-3 1.34-3 3s1.34 3 3 3zm-8 0c1.66 0 2.99-1.34 2.99-3S9.66 5 8 5C6.34 5 5 6.34 5 8s1.34 3 3 3zm0 2c-2.33 0-7 1.17-7 3.5V19h14v-2.5c0-2.33-4.67-3.5-7-3.5zm8 0c-.29 0-.62.02-.97.05 1.16.84 1.97 1.97 1.97 3.45V19h6v-2.5c0-2.33-4.67-3.5-7-3.5z"/>
                  </svg>
                </div>
                <div class="stat-simple-content">
                  <div class="stat-simple-number">{{ animatedStats.employees }}</div>
                  <div class="stat-simple-label">Total Employees</div>
                  <div class="stat-simple-line"></div>
                </div>
              </div>
            </div>
          </div>
          <div class="why-choose-right">
            <p class="why-intro">We also provide customized services for customers. According to different transportation needs, we can customize the cargo size, carrying capacity, tank body materials, loading and unloading equipment, etc. of tank semi-trailers to ensure that customers can obtain the most suitable solutions for their businesses.</p>
            <router-link to="/about" class="why-intro-button">Learn More</router-link>
            <div class="video-container">
              <iframe
                class="promo-video"
                src="https://www.youtube.com/embed/xcpsEGZPnLM"
                title="YouTube video player"
                frameborder="0"
                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                allowfullscreen>
              </iframe>
            </div>
          </div>
        </div>
      </div>
    <Brands />
          <div class="product-section">
        <div class="product-section-inner">
          <h2 class="section-title">Popular Products</h2>
        <p class="section-subtitle">We Promise To Find You The Right Equipment</p>
        <div class="category-tabs">
          <div
            class="category-tab"
            :class="{ active: currentCategory === 'truck' }"
            @click="setCategory('truck')"
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
          <div class="product-col" v-for="product in hotProducts" :key="product.id">
            <el-card class="product-card" :body-style="{ padding: '0px', height: '100%' }"  :style="{ cursor: 'pointer' }">
              <div class="product-image">
                <LazyImage :src="product.image" class="product-img" />
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
    <div class="feature-boxes" ref="featureBoxesRef">
      <div class="feature-section-header" ref="featureSectionHeaderRef">
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

    <div class="customer-visits-section">
      <div class="section-header">
        <h2 class="section-title">Customer Visits</h2>
        <p class="section-subtitle">Valued Partners from Around the World</p>
      </div>
      <div class="customer-visits-slider-wrapper">
        <div class="customer-visits-track" ref="customerVisitsTrackRef">
          <template v-if="visitImageCount > 0">
            <div class="customer-visit-item" v-for="i in 8" :key="i">
              <LazyImage :src="`/images/visit/visit${((i - 1) % visitImageCount) + 1}.jpg`" alt="Customer Visit" />
            </div>
          </template>
          <template v-else>
            <div class="customer-visit-item" v-for="i in 4" :key="i">
              <div class="no-images-placeholder">No images available</div>
            </div>
          </template>
        </div>
      </div>
    </div>

    <div class="solution-section">
      <div class="solution-container">
        <h2 class="section-title">Solutions</h2>
        <p class="section-subtitle">Professional Trailer Solutions for Your Business</p>
        <div class="solution-content" v-if="currentSolution">
          <div class="solution-card-wrapper">
            <div class="solution-card">
              <div class="solution-header">
                <h3 class="solution-title">{{ currentSolution.title }}</h3>
                <p class="solution-desc">{{ currentSolution.desc }}</p>
              </div>
              <div class="solution-buttons">
                <button class="solution-nav-btn prev" @click="prevSolution" :disabled="currentSolutionIndex === 0">
                  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor">
                    <path d="M15.41 7.41L14 6l-6 6 6 6 1.41-1.41L10.83 12z"/>
                  </svg>
                </button>
                <button class="solution-nav-btn next" @click="nextSolution" :disabled="currentSolutionIndex === solutions.length - 1 || solutions.length === 0">
                  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor">
                    <path d="M10 6L8.59 7.41 13.17 12l-4.58 4.59L10 18l6-6z"/>
                  </svg>
                </button>
              </div>
            </div>
          </div>
          <img :src="currentSolution.image" :alt="currentSolution.title" class="solution-image" />
        </div>
      </div>
    </div>



        <div class="cta-section">
      <div class="cta-content">
        <h2 class="cta-title">IF YOU HAVE ANY ENQUIRY ABOUT QUOTATION OR COOPERATION, PLEASE FEEL FREE TO EMAIL US</h2>
        <el-button type="primary" round class="cta-button" @click="openContactDialog">Contact Us</el-button>
      </div>
    </div>
    <Footer />

    <ContactFixed
      phone="+86-15588751133"
      email="info@zhishuntruck.com"
      whatsapp="+86-15588751133"
    />

    <CustomerService />

    <el-dialog v-model="contactDialogVisible" title="Contact Us" :width="dialogWidth" :close-on-click-modal="false" class="contact-dialog">
      <el-form :model="contactForm" label-position="top" class="contact-form">
        <div class="dialog-form-row">
          <el-form-item label="Your Name" required class="dialog-form-item">
            <el-input v-model="contactForm.name" placeholder="Enter your name" />
          </el-form-item>
          <el-form-item label="Email Address" required class="dialog-form-item">
            <el-input v-model="contactForm.email" placeholder="Enter your email" />
          </el-form-item>
        </div>
        <div class="dialog-form-row">
          <el-form-item label="Phone / WhatsApp" class="dialog-form-item">
            <el-input v-model="contactForm.phone" placeholder="Enter phone or WhatsApp" />
          </el-form-item>
          <el-form-item label="Company" class="dialog-form-item">
            <el-input v-model="contactForm.company" placeholder="Enter company name" />
          </el-form-item>
        </div>

        <div class="dialog-form-row dialog-form-row-full">
          <el-form-item label="Subject" class="dialog-form-item-full">
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
      </el-form>
      <template #footer>
        <el-button @click="contactDialogVisible = false">Cancel</el-button>
        <el-button type="primary" :loading="isContactSubmitting" @click="submitContactForm">
          <div class="i-ep-position w-20 h-20" v-if="!isContactSubmitting"></div>
          {{ isContactSubmitting ? 'Sending...' : 'Send Message' }}
        </el-button>
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

const loadVisitImages = async () => {
  const modules = import.meta.glob('/public/images/visit/*.{png,jpg,jpeg,gif,svg,webp}');
  const files = await Promise.all(Object.keys(modules));
  return files.length;
};
</script>

<script setup lang="ts">
import TopBar from '@/components/TopBar.vue'
import Footer from '@/components/Footer.vue'
import ContactFixed from '@/components/ContactFixed.vue'
import CustomerService from '@/components/CustomerService.vue'
import Brands from '@/components/Brands.vue'
import LazyImage from '@/components/LazyImage.vue'
import { ElMessage } from 'element-plus'
import axios from 'axios'
import { useRouter } from 'vue-router'
import { getProductsByCategory, getProductById, getCarouselConfig, getSolutionsConfig, type Product, type CarouselItem, type Solution } from '@/data/products'
import { useProductsStore } from '@/stores/modules/products'

const router = useRouter()
const productsStore = useProductsStore()

const imageCount = ref(0);
const visitImageCount = ref(0);
const carouselItems = ref<CarouselItem[]>([]);
const solutions = ref<Solution[]>([]);

async function loadCarouselConfig() {
  carouselItems.value = await getCarouselConfig()
}

async function loadSolutionsConfig() {
  solutions.value = await getSolutionsConfig()
}

const currentCategory = ref<'truck' | 'excavator'>('truck')
const currentProducts = ref<Product[]>([])

const hotProducts = computed(() => {
  const allHot = productsStore.getHotProducts()
  if (currentCategory.value === 'truck') {
    return allHot.filter(p => p.category === 'truck').slice(0, 6)
  } else {
    return allHot.filter(p => p.category === 'excavator').slice(0, 6)
  }
})

async function loadProducts() {
  currentProducts.value = await getProductsByCategory(currentCategory.value)
}

onMounted(async () => {
  imageCount.value = await loadCarouselImages()
  visitImageCount.value = await loadVisitImages()
  await loadCarouselConfig()
  await loadSolutionsConfig()
  await productsStore.loadProducts()
  await nextTick();
})

function setCategory(category: 'truck' | 'excavator') {
  currentCategory.value = category
  loadProducts()
}

async function goToProduct(id: number) {
  const product = await getProductById(id)
  if (product) {
    if (product.category === 'truck') {
      router.push(`/truck/${id}`)
    } else if (product.category === 'excavator') {
      router.push(`/excavator/${id}`)
    }
  }
}

const contactDialogVisible = ref(false)
const isContactSubmitting = ref(false)
const dialogWidth = ref('600px')

onMounted(() => {
  updateDialogWidth()
  window.addEventListener('resize', updateDialogWidth)
})

onUnmounted(() => {
  window.removeEventListener('resize', updateDialogWidth)
})

function updateDialogWidth() {
  if (window.innerWidth < 576) {
    dialogWidth.value = '90%'
  } else if (window.innerWidth < 768) {
    dialogWidth.value = '95%'
  } else {
    dialogWidth.value = '600px'
  }
}

interface ContactForm {
  name: string
  email: string
  phone: string
  company: string
  subject: string
  message: string
}

const contactForm = reactive<ContactForm>({
  name: '',
  email: '',
  phone: '',
  company: '',
  subject: '',
  message: ''
})

function openContactDialog() {
  contactDialogVisible.value = true
}

async function submitContactForm() {
  if (isContactSubmitting.value) return

  if (!contactForm.name || !contactForm.email || !contactForm.message) {
    ElMessage.warning('Please fill in all required fields')
    return
  }

  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
  if (!emailRegex.test(contactForm.email)) {
    ElMessage.warning('Please enter a valid email address')
    return
  }

  isContactSubmitting.value = true

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
      ElMessage.success(response.data.data || 'Message sent successfully!')
      contactDialogVisible.value = false
      Object.assign(contactForm, { name: '', email: '', phone: '', company: '', subject: '', message: '' })
    } else if (response.data.code === 40002) {
      ElMessage.warning(response.data.message || 'Duplicate request detected')
    } else {
      ElMessage.error(response.data.message || 'Failed to send message')
    }
  } catch (error) {
    console.error('Failed to submit form:', error)
    ElMessage.error('Failed to send message. Please try again.')
  } finally {
    isContactSubmitting.value = false
  }
}

const currentSolutionIndex = ref(0)

const currentSolution = computed<Solution>(() => {
  if (solutions.value.length === 0) {
    return { title: '', desc: '', image: '' } as Solution
  }
  console.log(solutions)
  return solutions.value[currentSolutionIndex.value] ?? solutions.value[0]!
})

function prevSolution() {
  if (currentSolutionIndex.value > 0) {
    currentSolutionIndex.value--
  }
}

function nextSolution() {
  if (currentSolutionIndex.value < solutions.value.length - 1) {
    currentSolutionIndex.value++
  }
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
const featureSectionHeaderRef = ref()
const isFeatureBoxesVisible = ref(false)

const customerVisitsTrackRef = ref()
const customerVisitsState = ref({
  currentIndex: 0,
  isAnimating: false
})
let customerVisitsTimer: ReturnType<typeof setInterval> | null = null

const whyChooseRef = ref()
const isWhyChooseVisible = ref(false)

const animatedStats = reactive<{
  exhibitionHall: string | number
  productionArea: string | number
  employees: string | number
}>({
  exhibitionHall: 0,
  productionArea: 0,
  employees: 0
})

const targetStats = {
  exhibitionHall: 5000,
  productionArea: 25000,
  employees: 1000
}

function animateNumber(start: number, end: number, duration: number, callback: (value: number) => void) {
  const startTime = performance.now()

  function update(currentTime: number) {
    const elapsed = currentTime - startTime
    const progress = Math.min(elapsed / duration, 1)

    const easeOutQuart = 1 - Math.pow(1 - progress, 4)
    const current = Math.floor(start + (end - start) * easeOutQuart)

    callback(current)

    if (progress < 1) {
      requestAnimationFrame(update)
    }
  }

  requestAnimationFrame(update)
}

function startNumberAnimation() {
  const duration = 4000

  animateNumber(0, targetStats.exhibitionHall, duration, (value) => {
    animatedStats.exhibitionHall = value.toLocaleString()
  })

  animateNumber(0, targetStats.productionArea, duration, (value) => {
    animatedStats.productionArea = value.toLocaleString()
  })

  animateNumber(0, targetStats.employees, duration, (value) => {
    animatedStats.employees = value.toLocaleString()
  })
}

function slideCustomerVisits() {
  if (customerVisitsState.value.isAnimating) return

  customerVisitsState.value.isAnimating = true
  customerVisitsState.value.currentIndex++

  if (customerVisitsTrackRef.value) {
    customerVisitsTrackRef.value.style.transform = `translateX(-${customerVisitsState.value.currentIndex * 380}px)`
    customerVisitsTrackRef.value.style.transition = 'transform 0.5s ease-in-out'
  }

  setTimeout(() => {
    customerVisitsState.value.isAnimating = false
    if (customerVisitsState.value.currentIndex >= 4) {
      customerVisitsState.value.currentIndex = 0
      if (customerVisitsTrackRef.value) {
        customerVisitsTrackRef.value.style.transition = 'none'
        customerVisitsTrackRef.value.style.transform = 'translateX(0)'
      }
    }
  }, 500)
}

function startCustomerVisitsSlider() {
  if (!customerVisitsTimer) {
    customerVisitsTimer = setInterval(slideCustomerVisits, 3000)
  }
}

onMounted(() => {
  const featureBoxesObserver = new IntersectionObserver(
    (entries) => {
      entries.forEach((entry) => {
        if (entry.isIntersecting) {
          isFeatureBoxesVisible.value = true
          featureBoxesObserver.disconnect()
        }
      })
    },
    { threshold: 0.5 }
  )

  if (featureSectionHeaderRef.value) {
    featureBoxesObserver.observe(featureSectionHeaderRef.value)
  }

  const customerVisitsObserver = new IntersectionObserver(
    (entries) => {
      entries.forEach((entry) => {
        if (entry.isIntersecting) {
          startCustomerVisitsSlider()
        } else {
          if (customerVisitsTimer) {
            clearInterval(customerVisitsTimer)
            customerVisitsTimer = null
          }
        }
      })
    },
    { threshold: 0.3 }
  )

  if (customerVisitsTrackRef.value) {
    customerVisitsObserver.observe(customerVisitsTrackRef.value)
  }

  const whyChooseObserver = new IntersectionObserver(
    (entries) => {
      entries.forEach((entry) => {
        if (entry.isIntersecting && !isWhyChooseVisible.value) {
          isWhyChooseVisible.value = true
          startNumberAnimation()
        }
      })
    },
    { threshold: 0.6 }
  )

  if (whyChooseRef.value) {
    whyChooseObserver.observe(whyChooseRef.value)
  }
})

</script>

<style scoped>
.carousel-container {
  position: relative;
  width: 100%;
  overflow-x: hidden;
  user-select: none;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
}

.carousel-image-wrapper {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}

.carousel-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  object-position: center;
  display: block;
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

.carousel-button {
  display: inline-block;
  margin-top: 40px;
  padding: 16px 48px;
  font-size: 18px;
  font-weight: 600;
  color: #ffffff;
  background: linear-gradient(135deg, #FF4500 0%, #FF0000 100%);
  border: 2px solid #FF4500;
  border-radius: 50px;
  text-decoration: none;
  text-transform: uppercase;
  letter-spacing: 2px;
  cursor: pointer;
  transition: all 0.3s ease;
  box-shadow: 0 4px 20px rgba(255, 69, 0, 0.4);
  animation: fadeInUp 0.8s ease-out 0.8s forwards;
  opacity: 0;
}

.carousel-button:hover {
  background: linear-gradient(135deg, #FF0000 0%, #CC0000 100%);
  border-color: #FF0000;
  box-shadow: 0 6px 30px rgba(255, 0, 0, 0.5);
  transform: translateY(-3px);
}

.carousel-button:active {
  transform: translateY(-1px);
  box-shadow: 0 4px 15px rgba(255, 0, 0, 0.4);
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
  transition: opacity 1.5s cubic-bezier(0.34, 1.56, 0.64, 1), transform 1.5s cubic-bezier(0.34, 1.56, 0.64, 1);
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
  overflow-x: hidden;
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
  /**background: #e54343;*/
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


.category-tab.active .category-name {
  color: #ffffff;
}
.category-tab:hover  .category-name{
  color: #000;
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
  /* border-radius: 16px; */
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
  overflow-x: hidden;
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
  background: linear-gradient(180deg, rgba(255, 255, 255, 0.95) 0%, rgba(246, 245, 237, 0.95) 100%), url('/images/about/image.png') center center no-repeat;
  background-size: cover;
  padding: 100px 20px;
}

.why-choose-container {
  max-width: 1400px;
  margin: 0 auto;
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 60px;
  align-items: flex-start;
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

.about-subtitle {
  font-size: 18px;
  font-weight: 500;
  color: #1a2a4a;
  line-height: 1.6;
  margin: 10px 0 25px 0;
}

.why-intro {
  font-size: 16px;
  line-height: 1.8;
  color: #555555;
  margin: 30px 0;
}

.why-intro-button {
  display: inline-block;
  padding: 12px 32px;
  font-size: 15px;
  font-weight: 600;
  color: #ffffff;
  background: #FF0000;
  border-radius: 25px;
  text-decoration: none;
  transition: all 0.3s ease;
  margin:20px 0;
}

.why-intro-button:hover {
  background: #cc0000;
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(255, 0, 0, 0.3);
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

.extra-stats {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 20px;
  margin-top: 30px;
  padding-top: 30px;
  border-top: 1px solid #e8e8e8;
}

.stat-simple-item {
  display: flex;
  align-items: flex-start;
  gap: 12px;
}

.stat-simple-icon {
  width: 36px;
  height: 36px;
  flex-shrink: 0;
  color: #1a2a4a;
}

.stat-simple-icon svg {
  width: 100%;
  height: 100%;
}

.stat-simple-content {
  display: flex;
  flex-direction: column;
  flex: 1;
  min-width: 0;
}

.stat-simple-number {
  font-size: 32px;
  font-weight: 700;
  color: #FF0000;
  line-height: 1.2;
}

.stat-simple-label {
  font-size: 15px;
  color: #666666;
  margin-top: 8px;
  line-height: 1.4;
}

.stat-simple-line-wrapper {
  width: 100%;
  height: 3px;
  margin-top: 10px;
  background: #e8e8e8;
  border-radius: 2px;
  overflow: hidden;
}

.stat-simple-line {
  width: 50%;
  height: 100%;
  background: #FF0000;
}

.extra-stats {
  display: flex;
  flex-direction: column;
  gap: 16px;
  margin-top: 30px;
  padding-top: 30px;
  border-top: 1px solid #e8e8e8;
}

.stat-bar-item {
  width: 100%;
}

.stat-bar-card {
  display: flex;
  align-items: center;
  gap: 16px;
  padding: 16px 20px;
  background: linear-gradient(135deg, #ff0000 0%, #cc0000 100%);
  border-radius: 12px;
  box-shadow: 0 4px 15px rgba(255, 0, 0, 0.3);
}

.stat-bar-fill-vertical {
  display: flex;
  align-items: center;
  gap: 12px;
  flex-shrink: 0;
}

.stat-bar-icon {
  width: 36px;
  height: 36px;
  color: #ffffff;
}

.stat-bar-icon svg {
  width: 100%;
  height: 100%;
}

.stat-bar-number {
  font-size: 24px;
  font-weight: 700;
  color: #ffffff;
  white-space: nowrap;
}

.stat-bar-label {
  font-size: 14px;
  color: #ffffff;
  opacity: 0.95;
}

@media (max-width: 768px) {
  .extra-stats {
    gap: 12px;
  }

  .stat-bar-card {
    padding: 14px 16px;
    flex-direction: column;
    text-align: center;
    gap: 10px;
  }

  .stat-bar-fill-vertical {
    flex-direction: column;
    gap: 8px;
  }

  .stat-bar-number {
    font-size: 22px;
  }

  .stat-bar-label {
    font-size: 13px;
  }
}

@media (max-width: 768px) {
  .extra-stats {
    grid-template-columns: 1fr;
    gap: 16px;
  }

  .extra-stat-item {
    justify-content: center;
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

@media (max-width: 992px) {
  .why-choose-left {
    padding-right: 0;
    order: 2;
  }
  .why-choose-right {
    order: 1;
  }
}

.why-choose-right {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
}

.why-choose-right .why-intro {
  margin: 0 0 20px 0;
}

.video-container {
  position: relative;
  width: 100%;
  max-width: 670px;
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

.customer-visits-section {
  padding: 80px 20px;
  background: #ffffff;
  overflow: hidden;
}

.customer-visits-section .section-header {
  text-align: center;
  margin-bottom: 50px;
}

.customer-visits-section .section-title {
  font-size: 36px;
  font-weight: 700;
  color: #1a1a2e;
  margin-bottom: 10px;
}

.customer-visits-section .section-subtitle {
  font-size: 16px;
  color: #666;
}

.customer-visits-slider-wrapper {
  width: 100%;
  max-width: calc(360px * 4 + 20px * 3);
  margin: 0 auto;
  /* padding: 0 20px; */
  overflow: hidden;
  box-sizing: border-box;
}

.customer-visits-track {
  display: flex;
  gap: 20px;
  transition: none;
  width: max-content;
}

.customer-visit-item {
  flex-shrink: 0;
  width: 360px;
  height: 270px;
  /* border-radius: 12px; */
  overflow: hidden;
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.customer-visit-item:hover {
  transform: scale(1.02);
  box-shadow: 0 12px 32px rgba(0, 0, 0, 0.15);
}

.customer-visit-item img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  display: block;
}

.no-images-placeholder {
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  background: #f5f5f5;
  color: #999;
  font-size: 14px;
}

.solution-section {
  padding: 80px 20px;
  background: linear-gradient(180deg, #F6F5ED 0%, #ffffff 100%);
}

.solution-container {
  max-width: 1400px;
  margin: 0 auto;
}

.solution-content {
  position: relative;
  margin-top: 50px;
  box-shadow: 0 20px 60px rgba(0, 0, 0, 0.15);
}

.solution-image {
  width: 100%;
  height: 500px;
  object-fit: cover;
  object-position: center;
  display: block;
}

@media (max-width: 992px) {
  .solution-image {
    height: 450px;
  }
}

@media (max-width: 768px) {
  .solution-image {
    height: 400px;
  }
}

.solution-card-wrapper {
  position: absolute;
  top: 0;
  bottom: 0;
  right: 0;
  width: 100%;
  max-width: 480px;
  z-index: 1;
}

.solution-card {
  background: rgba(38, 37, 33, 0.7);
  padding: 40px;
  box-shadow: 0 20px 60px rgba(0, 0, 0, 0.2);
  animation: solutionCardIn 0.5s ease-out;
  height: 100%;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

@keyframes solutionCardIn {
  from {
    opacity: 0;
    transform: translateX(30px);
  }
  to {
    opacity: 1;
    transform: translateX(0);
  }
}

.solution-header {
  margin-bottom: 30px;
}

.solution-title {
  font-size: 32px;
  font-weight: 700;
  color: #ffffff;
  margin: 0 0 16px 0;
  line-height: 1.3;
}

.solution-desc {
  font-size: 16px;
  color: rgba(255, 255, 255, 0.85);
  line-height: 1.7;
  margin: 0;
}

.solution-buttons {
  display: flex;
  gap: 16px;
}

.solution-nav-btn {
  width: 56px;
  height: 56px;
  border-radius: 50%;
  border: 2px solid rgba(255, 255, 255, 0.5);
  background: rgba(255, 255, 255, 0.15);
  color: #ffffff;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: all 0.3s ease;
}

.solution-nav-btn:hover:not(:disabled) {
  background: #ffffff;
  color: rgba(38, 37, 33, 0.7);
  border-color: #ffffff;
  transform: scale(1.05);
}

.solution-nav-btn:disabled {
  opacity: 0.4;
  cursor: not-allowed;
}

.solution-nav-btn svg {
  width: 28px;
  height: 28px;
}

@media (max-width: 992px) {
  .solution-card-wrapper {
    right: 0;
    max-width: 400px;
  }

  .solution-image {
    height: 400px;
  }

  .solution-card {
    padding: 30px;
  }

  .solution-title {
    font-size: 26px;
  }
}

@media (max-width: 768px) {
  .solution-card-wrapper {
    position: relative;
    top: auto;
    right: auto;
    max-width: 100%;
    margin: -60px 20px 0;
  }

  .solution-content {
    margin-top: 40px;
  }

  .solution-image {
    height: 350px;
  }

  .solution-card {
    padding: 30px 24px;
    border-radius: 16px;
  }

  .solution-title {
    font-size: 22px;
  }

  .solution-desc {
    font-size: 14px;
  }

  .solution-nav-btn {
    width: 48px;
    height: 48px;
  }

  .solution-nav-btn svg {
    width: 24px;
    height: 24px;
  }
}

@media (max-width: 576px) {
  .solution-section {
    padding: 60px 16px;
  }

  .solution-card {
    padding: 20px 16px;
  }

  .solution-title {
    font-size: 18px;
  }

  .solution-header {
    margin-bottom: 16px;
  }

  .solution-buttons {
    gap: 10px;
  }

  .contact-dialog {
    width: 100%;
    max-width: calc(100% - 16px);
  }

  .contact-dialog .el-dialog {
    width: 100% !important;
    max-width: 100%;
    margin: 8px auto !important;
    border-radius: 10px;
  }

  .contact-dialog .el-dialog__header {
    padding: 14px 16px 10px;
  }

  .contact-dialog .el-dialog__title {
    font-size: 18px;
  }

  .contact-dialog .el-dialog__body {
    padding: 16px;
  }

  .contact-dialog .el-dialog__headerbtn {
    top: 10px;
    right: 10px;
  }

  .contact-dialog .el-dialog__headerbtn .el-dialog__close {
    font-size: 18px;
  }

  .contact-dialog .el-dialog__footer {
    padding: 12px 16px;
    flex-direction: column;
    gap: 10px;
  }

  .contact-dialog :deep(.el-dialog__footer .el-button) {
    width: 100%;
    justify-content: center;
    padding: 10px 20px;
    font-size: 14px;
  }

  .contact-dialog :deep(.el-form-item) {
    margin-bottom: 12px;
  }

  .contact-dialog :deep(.el-form-item__label) {
    font-size: 13px;
    padding-bottom: 4px;
  }

  .contact-dialog :deep(.el-input__inner) {
    font-size: 14px;
    height: 40px;
  }

  .contact-dialog :deep(.el-textarea__inner) {
    font-size: 14px;
    min-height: 80px !important;
  }

  .dialog-form-row {
    grid-template-columns: 1fr;
    gap: 10px;
  }
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

  .contact-dialog {
    width: 100%;
    max-width: calc(100% - 20px);
  }

  .contact-dialog .el-dialog {
    width: 100% !important;
    max-width: 100%;
    margin: 10px auto !important;
    border-radius: 12px;
  }

  .contact-dialog .el-dialog__header {
    padding: 16px 20px 12px;
  }

  .contact-dialog .el-dialog__title {
    font-size: 20px;
  }

  .contact-dialog .el-dialog__body {
    padding: 20px;
  }

  .contact-dialog .el-dialog__footer {
    padding: 12px 20px 16px;
  }

  .contact-dialog :deep(.el-form-item) {
    margin-bottom: 14px;
  }

  .contact-dialog :deep(.el-form-item__label) {
    font-size: 13px;
  }

  .contact-dialog :deep(.el-input__inner) {
    font-size: 14px;
  }

  .dialog-form-row {
    grid-template-columns: 1fr;
    gap: 12px;
  }

  .dialog-form-item-full {
    grid-column: 1;
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

.dialog-form-row {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 20px;
}

.dialog-form-row-full {
  grid-template-columns: 1fr;
}

.dialog-form-item,
.dialog-form-item-full {
  width: 100%;
}

.contact-dialog :deep(.el-dialog) {
  border-radius: 16px;
}

.contact-dialog :deep(.el-dialog__header) {
  padding: 20px 24px 16px;
  border-bottom: 1px solid #e2e8f0;
}

.contact-dialog :deep(.el-dialog__title) {
  font-size: 20px;
  font-weight: 700;
  color: #1a2a4a;
}

.contact-dialog :deep(.el-dialog__body) {
  padding: 24px;
}

.contact-dialog :deep(.el-form-item) {
  margin-bottom: 16px;
}

.contact-dialog :deep(.el-form-item__label) {
  font-weight: 500;
  color: #1a2a4a;
  font-size: 14px;
}

.contact-dialog :deep(.el-input__wrapper),
.contact-dialog :deep(.el-textarea__wrapper) {
  border-radius: 8px;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.08);
  transition: all 0.3s ease;
  background-color: #F6F6F6;
}

.contact-dialog :deep(.el-input__wrapper:hover),
.contact-dialog :deep(.el-textarea__wrapper:hover) {
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.12);
  background-color: #F0F0F0;
}

.contact-dialog :deep(.el-input__wrapper:focus-within),
.contact-dialog :deep(.el-textarea__wrapper:focus-within) {
  box-shadow: 0 2px 12px rgba(255, 0, 0, 0.15);
  border-color: #FF0000;
  background-color: #FFFFFF;
}

.contact-dialog :deep(.el-dialog__footer) {
  padding: 16px 24px 20px;
  border-top: 1px solid #e2e8f0;
}

.contact-dialog :deep(.el-dialog__footer .el-button--primary) {
  background: linear-gradient(135deg, #FF0000 0%, #cc0000 100%);
  border: none;
  border-radius: 8px;
  padding: 12px 24px;
  font-weight: 600;
  display: flex;
  align-items: center;
  gap: 8px;
  transition: all 0.3s ease;
}

.contact-dialog :deep(.el-dialog__footer .el-button--primary:hover) {
  background: linear-gradient(135deg, #cc0000 0%, #aa0000 100%);
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(255, 0, 0, 0.3);
}

@media (max-width: 480px) {
  .carousel-container {
    min-height: auto;
  }

  .el-carousel {
    height: 400px !important;
  }

  .el-carousel-item {
    height: 400px !important;
  }

  .carousel-image-wrapper {
    height: 100% !important;
  }

  .carousel-img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    object-position: center center;
  }

  .title-container {
    padding: 0 15px;
    bottom: 40px;
  }

  .main-title {
    font-size: 22px !important;
    line-height: 1.3;
    margin-bottom: 8px;
  }

  .carousel-button {
    padding: 10px 24px;
    font-size: 14px;
    margin-top: 15px;
  }

  .why-choose-section {
    padding: 50px 15px;
  }

  .why-choose-container {
    gap: 30px;
  }

  .about-subtitle {
    font-size: 16px;
  }

  .why-stats {
    gap: 15px;
    margin-top: 25px;
  }

  .stat-item {
    flex: 1;
    min-width: 55px;
  }

  .stat-number {
    font-size: 20px;
  }

  .stat-label {
    font-size: 9px;
    letter-spacing: 0.3px;
  }

  .extra-stats {
    gap: 20px;
    padding-top: 20px;
    margin-top: 20px;
  }

  .stat-bar-card {
    flex-direction: row;
    padding: 12px 15px;
  }

  .stat-bar-fill-vertical {
    flex-direction: row;
    gap: 10px;
  }

  .stat-bar-number {
    font-size: 18px;
  }

  .stat-bar-label {
    font-size: 12px;
  }

  .stat-simple-item {
    gap: 10px;
  }

  .stat-simple-icon {
    width: 28px;
    height: 28px;
  }

  .stat-simple-content {
    flex: 1;
    min-width: 0;
  }

  .stat-simple-number {
    font-size: 22px;
  }

  .stat-simple-label {
    font-size: 11px;
    margin-top: 6px;
  }

  .stat-simple-line-wrapper {
    margin-top: 6px;
    height: 2px;
  }

  .why-intro {
    font-size: 14px;
    line-height: 1.7;
    margin: 20px 0;
  }

  .why-intro-button {
    padding: 10px 26px;
    font-size: 14px;
    margin: 15px 0;
  }

  .video-container {
    border-radius: 12px;
  }

  .brands-section {
    padding: 40px 15px;
  }

  .product-section {
    padding: 40px 0;
  }

  .product-section-inner {
    padding: 0 15px;
  }

  .section-title {
    font-size: 24px;
  }

  .section-subtitle {
    font-size: 14px;
    margin-bottom: 30px;
  }

  .category-tabs {
    gap: 12px;
  }

  .category-tab {
    padding: 12px 20px;
  }

  .category-name {
    font-size: 14px;
  }

  .product-grid {
    gap: 15px;
  }

  .product-col {
    width: calc(50% - 8px) !important;
  }

  .product-card {
    height: auto;
    min-height: 240px;
  }

  .product-image {
    height: 120px;
    margin: 12px auto 0;
  }

  .product-name {
    font-size: 14px;
  }

  .product-content {
    padding: 10px 12px;
  }

  .product-footer .el-button {
    padding: 6px 14px;
    font-size: 12px;
  }

  .features-section {
    padding: 50px 15px;
  }

  .features-grid {
    gap: 15px;
  }

  .feature-card {
    padding: 20px;
  }

  .feature-icon {
    width: 48px;
    height: 48px;
    margin-bottom: 15px;
  }

  .feature-title {
    font-size: 18px;
  }

  .feature-desc {
    font-size: 13px;
  }

  .feature-boxes {
    padding: 50px 15px;
    overflow: visible;
  }

  .feature-section-header {
    margin-bottom: 25px;
  }

  .feature-boxes-inner {
    flex-direction: column;
    padding: 0 15px;
    gap: 20px;
  }

  .feature-box {
    width: 100% !important;
    flex: none;
    padding: 30px 25px;
    border-radius: 16px;
    min-height: auto;
    background-position: center;
    background-size: cover;
    box-sizing: border-box;
  }

  .feature-box::before {
    background: rgba(255, 255, 255, 0.85);
    border-radius: 16px;
  }

  .feature-box .feature-title {
    font-size: 18px;
    margin-bottom: 10px;
  }

  .feature-box .feature-desc {
    font-size: 14px;
    line-height: 1.6;
  }

  .feature-section-header {
    margin-bottom: 30px;
    padding: 0 15px;
  }

  .feature-section-header .section-title {
    font-size: 24px;
  }

  .feature-section-header .section-subtitle {
    font-size: 14px;
    padding: 0 20px;
  }

  .customer-visits-section {
    padding: 50px 15px;
    overflow-x: visible;
    overflow-y: hidden;
  }

  .customer-visits-slider-wrapper {
    width: 100%;
    max-width: 100%;
    padding: 0;
    overflow-x: auto;
    overflow-y: hidden;
    -webkit-overflow-scrolling: touch;
    scrollbar-width: none;
    scroll-snap-type: x mandatory;
  }

  .customer-visits-slider-wrapper::-webkit-scrollbar {
    display: none;
  }

  .customer-visits-track {
    display: flex;
    gap: 12px;
    width: max-content;
    transition: none;
    scroll-snap-type: x mandatory;
  }

  .customer-visit-item {
    flex-shrink: 0;
    width: calc(100vw - 60px);
    max-width: 320px;
    height: 220px;
    border-radius: 12px;
    scroll-snap-align: center;
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
  }

  .customer-visit-item:hover {
    transform: scale(1.02);
    box-shadow: 0 8px 25px rgba(0, 0, 0, 0.15);
  }

  .customer-visits-section .section-header {
    margin-bottom: 30px;
  }

  .customer-visits-section .section-title {
    font-size: 24px;
  }

  .customer-visits-section .section-subtitle {
    font-size: 14px;
  }

  .solution-section {
    padding: 40px 15px;
    background: linear-gradient(180deg, #F6F5ED 0%, #ffffff 50%, #F6F5ED 100%);
  }

  .solution-container {
    max-width: 100%;
    padding: 0;
  }

  .solution-section .section-title {
    padding: 0 15px !important;
    margin-left: auto !important;
    margin-right: auto !important;
    max-width: calc(100% - 30px) !important;
    width: 100% !important;
    box-sizing: border-box !important;
    color: #1a2a4a !important;
    font-weight: 700 !important;
  }

  .solution-section .section-subtitle {
    padding: 0 15px !important;
    margin-left: auto !important;
    margin-right: auto !important;
    max-width: calc(100% - 30px) !important;
    width: 100% !important;
    box-sizing: border-box !important;
    color: #666 !important;
    font-size: 14px !important;
  }

  .solution-section h2.section-title {
    margin-top: 0 !important;
    margin-bottom: 8px !important;
  }

  .solution-section p.section-subtitle {
    margin-top: 0 !important;
    margin-bottom: 0 !important;
  }

  .solution-content {
    margin-top: 25px;
    width: 100%;
    display: flex;
    flex-direction: column;
    border-radius: 16px;
    overflow: hidden;
    box-shadow: 0 10px 40px rgba(0, 0, 0, 0.12);
    background: #1a2a4a;
  }

  .solution-image {
    width: 100%;
    height: 200px;
    object-fit: cover;
    object-position: center;
  }

  .solution-card-wrapper {
    position: relative;
    top: auto;
    right: auto;
    left: auto;
    width: 100%;
    max-width: 100%;
    margin: 0;
    padding: 0;
  }

  .solution-card {
    padding: 24px 20px;
    background: linear-gradient(135deg, #1a2a4a 0%, #2d3a5c 100%);
    border-radius: 0;
    display: flex;
    flex-direction: column;
    width: 100%;
    box-sizing: border-box;
  }

  .solution-header {
    margin-bottom: 16px;
  }

  .solution-title {
    font-size: 20px;
    font-weight: 700;
    color: #ffffff;
    margin: 0 0 10px 0;
    line-height: 1.3;
  }

  .solution-desc {
    font-size: 14px;
    color: rgba(255, 255, 255, 0.85);
    line-height: 1.6;
    margin: 0;
  }

  .solution-buttons {
    display: flex;
    gap: 12px;
    margin-top: 20px;
    justify-content: center;
  }

  .solution-nav-btn {
    width: 48px;
    height: 48px;
    border-radius: 50%;
    border: 2px solid rgba(255, 255, 255, 0.4);
    background: rgba(255, 255, 255, 0.1);
    color: #ffffff;
    cursor: pointer;
    display: flex;
    align-items: center;
    justify-content: center;
    transition: all 0.3s ease;
  }

  .solution-nav-btn:hover:not(:disabled) {
    background: #ffffff;
    color: #1a2a4a;
    border-color: #ffffff;
    transform: scale(1.08);
  }

  .solution-nav-btn:disabled {
    opacity: 0.35;
    cursor: not-allowed;
  }

  .solution-nav-btn svg {
    width: 24px;
    height: 24px;
    fill: currentColor;
  }

  .solution-indicator {
    display: flex;
    justify-content: center;
    gap: 8px;
    margin-top: 20px;
  }

  .solution-indicator-dot {
    width: 8px;
    height: 8px;
    border-radius: 50%;
    background: rgba(26, 42, 74, 0.3);
    cursor: pointer;
    transition: all 0.3s ease;
  }

  .solution-indicator-dot.active {
    background: #FF0000;
    width: 24px;
    border-radius: 4px;
  }

  .cta-section {
    padding: 30px 15px;
  }

  .cta-content {
    flex-direction: column;
    gap: 20px;
  }

  .cta-title {
    font-size: 14px;
    text-align: center;
    min-width: 100%;
  }

  .cta-button {
    padding: 10px 28px;
    font-size: 14px;
  }

  .contact-dialog {
    width: 100%;
    max-width: calc(100% - 30px);
  }

  .contact-dialog .el-dialog {
    width: 100% !important;
    max-width: 100%;
  }

  .dialog-form-row {
    grid-template-columns: 1fr;
    gap: 12px;
  }

  .floating-topbar {
    padding: 10px 15px;
  }



  .customer-service {
    bottom: 15px;
    left: 15px;
  }

  .lazy-image {
    min-height: 120px;
  }
}

@media (max-width: 360px) {
  .el-carousel {
    height: 350px !important;
  }

  .el-carousel-item {
    height: 350px !important;
  }

  .main-title {
    font-size: 18px !important;
  }

  .carousel-button {
    padding: 8px 20px;
    font-size: 13px;
  }

  .product-col {
    width: 100% !important;
  }

  .customer-visit-item {
    width: calc(100vw - 50px);
    max-width: 280px;
    height: 200px;
  }

  .customer-visits-section {
    padding: 40px 12px;
    overflow-x: visible;
    overflow-y: hidden;
  }

  .customer-visits-section .section-title {
    font-size: 22px;
  }

  .customer-visits-section .section-subtitle {
    font-size: 13px;
  }

  .stat-bar-card {
    flex-direction: column;
    text-align: center;
  }

  .solution-section {
    padding: 40px 12px;
    background: linear-gradient(180deg, #F6F5ED 0%, #ffffff 50%, #F6F5ED 100%);
  }

  .solution-container {
    max-width: 100%;
    width: 100%;
    padding: 0 12px;
    box-sizing: border-box;
  }

  .solution-section .section-title {
    font-size: 20px;
    padding: 0 12px !important;
    margin-left: auto !important;
    margin-right: auto !important;
    max-width: calc(100% - 24px) !important;
    color: #1a2a4a !important;
    font-weight: 700 !important;
  }

  .solution-section .section-subtitle {
    font-size: 12px;
    padding: 0 12px !important;
    margin-left: auto !important;
    margin-right: auto !important;
    max-width: calc(100% - 24px) !important;
    color: #666 !important;
  }

  .solution-section h2.section-title {
    margin-top: 0 !important;
    margin-bottom: 8px !important;
  }

  .solution-section p.section-subtitle {
    margin-top: 0 !important;
    margin-bottom: 0 !important;
  }

  .solution-content {
    margin-top: 20px;
    width: 100%;
    border-radius: 12px;
    box-shadow: 0 8px 30px rgba(0, 0, 0, 0.12);
    background: #1a2a4a;
    overflow: hidden;
    display: flex;
    flex-direction: column;
    box-sizing: border-box;
  }

  .solution-image {
    width: 100%;
    height: 180px;
    object-fit: cover;
    object-position: center;
  }

  .solution-card {
    padding: 20px 15px;
    background: linear-gradient(135deg, #1a2a4a 0%, #2d3a5c 100%);
    border-radius: 0;
    display: flex;
    flex-direction: column;
    width: 100%;
    box-sizing: border-box;
  }

  .solution-header {
    margin-bottom: 12px;
  }

  .solution-title {
    font-size: 18px;
    font-weight: 700;
    color: #ffffff;
    margin: 0 0 8px 0;
    line-height: 1.3;
  }

  .solution-desc {
    font-size: 12px;
    color: rgba(255, 255, 255, 0.85);
    line-height: 1.5;
    margin: 0;
  }

  .solution-buttons {
    display: flex;
    gap: 10px;
    margin-top: 15px;
    justify-content: center;
  }

  .solution-nav-btn {
    width: 42px;
    height: 42px;
    border-radius: 50%;
    border: 2px solid rgba(255, 255, 255, 0.4);
    background: rgba(255, 255, 255, 0.1);
    color: #ffffff;
    cursor: pointer;
    display: flex;
    align-items: center;
    justify-content: center;
    transition: all 0.3s ease;
  }

  .solution-nav-btn:hover:not(:disabled) {
    background: #ffffff;
    color: #1a2a4a;
    border-color: #ffffff;
    transform: scale(1.08);
  }

  .solution-nav-btn:disabled {
    opacity: 0.35;
    cursor: not-allowed;
  }

  .solution-nav-btn svg {
    width: 20px;
    height: 20px;
    fill: currentColor;
  }

  .solution-indicator {
    display: flex;
    justify-content: center;
    gap: 6px;
    margin-top: 15px;
  }

  .solution-indicator-dot {
    width: 6px;
    height: 6px;
    border-radius: 50%;
    background: rgba(26, 42, 74, 0.3);
    cursor: pointer;
    transition: all 0.3s ease;
  }

  .solution-indicator-dot.active {
    background: #FF0000;
    width: 18px;
    border-radius: 3px;
  }

  .cta-title {
    font-size: 13px;
  }

  .feature-boxes {
    padding: 40px 12px;
    overflow: visible;
  }

  .feature-boxes-inner {
    padding: 0 12px;
    gap: 15px;
  }

  .feature-box {
    padding: 25px 20px;
    border-radius: 12px;
    box-sizing: border-box;
  }

  .feature-box .feature-title {
    font-size: 16px;
  }

  .feature-box .feature-desc {
    font-size: 13px;
  }

  .feature-section-header {
    margin-bottom: 20px;
    padding: 0 10px;
  }

  .feature-section-header .section-title {
    font-size: 22px;
  }

  .feature-section-header .section-subtitle {
    font-size: 13px;
  }
}
</style>
