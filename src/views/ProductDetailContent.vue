<template>
  <div class="products-page-container">
    <TopBar class="floating-topbar" />

    <div class="page-header">
      <div class="page-header-inner">
        <!-- <h1 class="page-title">Products</h1> -->
        <!-- <p class="page-description">Explore our wide range of high-quality products</p> -->
      </div>
    </div>

    <div class="products-main-section">
      <div class="products-main-inner">
        <div class="content-layout">
          <Sidebar
            :search-query="searchQuery"
            :current-category="currentCategory"
            :expanded-keys="expandedKeys"
            @update:searchQuery="searchQuery = $event"
            @category-change="handleCategoryChange"
          />

          <div class="product-detail" v-if="currentProduct">
            <el-breadcrumb separator="/" class="detail-breadcrumb">
              <el-breadcrumb-item :to="{ path: '/' }">Home</el-breadcrumb-item>
              <el-breadcrumb-item :to="{ path: '/products' }">Products</el-breadcrumb-item>
              <el-breadcrumb-item v-if="currentCategoryLabel && currentCategoryLabel !== 'All Products'" >
                {{ currentCategoryLabel }}
              </el-breadcrumb-item>
              <el-breadcrumb-item>{{ currentProduct.name }}</el-breadcrumb-item>
            </el-breadcrumb>

            <div class="product-detail-layout">
              <div class="product-image-section">
                <div
                  class="product-main-image"
                  @mousemove="handleMouseMove"
                  @mouseenter="handleMouseEnter"
                  @mouseleave="handleMouseLeave"
                >
                  <img :src="selectedImage" :alt="currentProduct.name" />
                </div>
                <div
                  class="zoom-preview"
                  v-if="isHovering"
                  :style="{
                    ...zoomStyle,
                    ...previewPosition
                  }"
                ></div>
                <div class="product-thumbnails-wrapper" v-if="allImages.length >= 1">
                  <button
                    class="thumbnail-nav-btn"
                    @click="prevImage"
                    v-if="allImages.length > 1"
                  >
                    <div class="i-ep-arrow-left w20 h20"></div>
                  </button>
                  <div class="product-thumbnails">
                    <div
                      class="thumbnail"
                      v-for="(img, index) in allImages"
                      :key="index"
                      :class="{ active: selectedImage === img }"
                      @click="selectImage(img)"
                    >
                      <img :src="img" :alt="`Image ${index + 1}`" />
                    </div>
                  </div>
                  <button
                    class="thumbnail-nav-btn"
                    @click="nextImage"
                    v-if="allImages.length > 1"
                  >
                    <div class="i-ep-arrow-right w20 h20"></div>
                  </button>
                </div>
              </div>

              <div class="product-detail-info">
                <h1 class="product-detail-title">{{ currentProduct.name }}</h1>

                <div class="product-specs-text" v-if="currentProduct.specsText">
                  <p v-for="(line, index) in currentProduct.specsText" :key="index">{{ line }}</p>
                </div>

                <div class="product-detail-actions">
                  <el-button type="primary" size="large" @click="handleContact">
                    <div class="i-ep-message w20 h20 mr8"></div>
                    Send Inquiry
                  </el-button>
                </div>
              </div>
            </div>

            <h3 class="product-intro-title" @click="toggleMarkdown">
              Product Introduction
              <span class="toggle-icon" :class="{ collapsed: !showMarkdown }">
                <div class="i-ep-arrow-down w-5 h-5"></div>
              </span>
            </h3>
            <el-collapse-transition>
              <div v-show="showMarkdown" class="product-detail-markdown" v-html="renderedMarkdown"></div>
            </el-collapse-transition>

            <div class="related-products-section">
              <h3 class="related-title">You Might Also Like</h3>
              <div class="related-grid">
                <div
                  class="related-card"
                  v-for="product in relatedProducts"
                  :key="product.id"
                  @click="goToProduct(product.id)"
                >
                  <div class="related-image">
                    <img :src="product.image" :alt="product.name" />
                  </div>
                  <div class="related-info">
                    <h4 class="related-name">{{ product.name }}</h4>
                    <p class="related-category">{{ product.category }}</p>
                  </div>
                </div>
              </div>
            </div>

            <div id="inquiry-form-section" class="inquiry-form-section">
              <h3 class="inquiry-title">Send Inquiry</h3>
              <p class="inquiry-subtitle">Interested in our products? Send us an inquiry and we'll get back to you soon.</p>
              <el-form :model="inquiryForm" label-position="top" class="inquiry-form">
                <div class="form-row">
                  <el-form-item label="Name" required class="form-item-half">
                    <el-input v-model="inquiryForm.name" placeholder="Your name" />
                  </el-form-item>
                  <el-form-item label="Email" required class="form-item-half">
                    <el-input v-model="inquiryForm.email" placeholder="Your email" />
                  </el-form-item>
                </div>
                <div class="form-row">
                  <el-form-item label="Phone" class="form-item-half">
                    <el-input v-model="inquiryForm.phone" placeholder="Your phone number" />
                  </el-form-item>
                  <el-form-item label="Company" class="form-item-half">
                    <el-input v-model="inquiryForm.company" placeholder="Your company name" />
                  </el-form-item>
                </div>
                <el-form-item label="Subject">
                  <el-input v-model="inquiryForm.subject" placeholder="Inquiry subject" />
                </el-form-item>
                <el-form-item label="Please include details like size, weight, destination port and etc" required>
                  <el-input v-model="inquiryForm.message" type="textarea" :rows="4" placeholder="Describe your requirements..." />
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" class="submit-btn" :loading="isSubmitting" @click="submitInquiry">
                    {{ isSubmitting ? 'Sending...' : 'Send Inquiry' }}
                  </el-button>
                </el-form-item>
              </el-form>
            </div>
          </div>

          <div class="product-not-found" v-else>
            <div class="not-found-icon">
              <div class="i-ep-box w64 h64"></div>
            </div>
            <h2 class="not-found-title">Product Not Found</h2>
            <p class="not-found-desc">Please select a product from the sidebar to view details</p>
          </div>
        </div>
      </div>
    </div>

    <Footer
      phone="+86-15588751133"
      email="info@zhishuntruck.com"
      whatsapp="+86-15588751133"
      address="Beijing, China"
    />

    <ContactFixed
      phone="+86-15588751133"
      email="info@zhishuntruck.com"
      whatsapp="+86-15588751133"
    />

    <CustomerService />
  </div>
</template>

<script setup lang="ts">
import TopBar from '@/components/TopBar.vue'
import Footer from '@/components/Footer.vue'
import ContactFixed from '@/components/ContactFixed.vue'
import CustomerService from '@/components/CustomerService.vue'
import Sidebar from '@/components/Sidebar.vue'
import { useRoute, useRouter } from 'vue-router'
import { ref, computed, onMounted, watch } from 'vue'
import { marked } from 'marked'
import { ElMessage } from 'element-plus'
import axios from 'axios'
import { getProducts, getProductById, getNewProducts, getCategoryTreeFromApi, type Product, type CategoryTreeNode } from '@/data/products'

interface Props {
  id?: number
  category?: string
}

interface InquiryForm {
  name: string
  email: string
  phone: string
  company: string
  subject: string
  message: string
}

const props = defineProps<Props>()

const route = useRoute()
const router = useRouter()

const searchQuery = ref('')
const currentCategory = ref<string>('all')
const expandedKeys = ref<string[]>([])

const currentProductId = computed(() => {
  const params = route.params as Record<string, string>
  const query = route.query as Record<string, string>
  const routeId = params.id || query.id
  if (routeId) {
    return parseInt(routeId)
  }
  return props.id || null
})

const currentProduct = ref<Product | null>(null)

async function loadCurrentProduct() {
  if (currentProductId.value) {
    const product = await getProductById(currentProductId.value)
    currentProduct.value = product || null
  }
}

watch(currentProductId, async () => {
  await loadCurrentProduct()
})

const selectedImage = ref('')
const showMarkdown = ref(true)

const isHovering = ref(false)
const mouseX = ref(0)
const mouseY = ref(0)
const zoomLevel = 2.5

const zoomStyle = computed(() => {
  if (!isHovering.value) return {}

  const bgSizeX = 480 * zoomLevel
  const bgSizeY = 360 * zoomLevel
  const bgPosX = mouseX.value * zoomLevel - 200
  const bgPosY = mouseY.value * zoomLevel - 180

  return {
    backgroundImage: `url(${selectedImage.value})`,
    backgroundSize: `${bgSizeX}px ${bgSizeY}px`,
    backgroundPosition: `-${bgPosX}px -${bgPosY}px`
  }
})

const previewPosition = computed(() => {
   if (!isHovering.value) return {}

   const previewWidth = 400
   const previewHeight = 400
   const offset = 20

   const imgSection = document.querySelector('.product-image-section')
   if (!imgSection) return {}

   const rect = imgSection.getBoundingClientRect()
   let left = rect.right + offset
   let top = rect.top

   if (left + previewWidth > window.innerWidth) {
     left = rect.left - previewWidth - offset
   }

   if (top + previewHeight > window.innerHeight - 20) {
     top = window.innerHeight - previewHeight - 20
   }

   return {
     left: `${left}px`,
     top: `${top}px`
   }
 })

function handleMouseMove(e: MouseEvent) {
  const target = e.currentTarget as HTMLElement
  const rect = target.getBoundingClientRect()
  mouseX.value = e.clientX - rect.left
  mouseY.value = e.clientY - rect.top
}

function handleMouseEnter() {
  isHovering.value = true
}

function handleMouseLeave() {
  isHovering.value = false
}

const allImages = computed(() => {
  if (!currentProduct.value) return []
  const images = [currentProduct.value.image]
  if (currentProduct.value.images && currentProduct.value.images.length > 0) {
    images.push(...currentProduct.value.images)
  }
  return images
})

const markdownContent = ref('')
const renderedMarkdown = computed(() => markdownContent.value)

onMounted(async () => {
  await loadMarkdown()
})

async function loadMarkdown() {
  try {
    const path = currentProduct.value?.markdownPath || '/md/product-intro.md'
    const response = await fetch(path)
    if (response.ok) {
      const text = await response.text()
      let html = marked.parse(text) as string
      html = html.replace(/<table([^>]*)style="[^"]*"/gi, '<table$1')
      html = html.replace(/<th([^>]*)style="[^"]*"/gi, '<th$1')
      html = html.replace(/<td([^>]*)style="[^"]*"/gi, '<td$1')
      html = html.replace(/<tr([^>]*)style="[^"]*"/gi, '<tr$1')
      html = html.replace(/<img\s+/gi, '<img loading="lazy" ')
      markdownContent.value = html
    }
  } catch (error) {
    console.error('Failed to load markdown:', error)
  }
}

watch(currentProduct, async (product) => {
  if (product) {
    selectedImage.value = product.image
    await loadMarkdown()
  }
}, { immediate: true })

function selectImage(image: string) {
  selectedImage.value = image
}

function prevImage() {
  const currentIndex = allImages.value.findIndex(img => img === selectedImage.value)
  if (currentIndex > 0 && allImages.value[currentIndex - 1]) {
    selectedImage.value = allImages.value[currentIndex - 1]!
  } else if (allImages.value.length > 0) {
    selectedImage.value = allImages.value[allImages.value.length - 1]!
  }
}

function nextImage() {
  const currentIndex = allImages.value.findIndex(img => img === selectedImage.value)
  if (currentIndex < allImages.value.length - 1 && allImages.value[currentIndex + 1]) {
    selectedImage.value = allImages.value[currentIndex + 1]!
  } else if (allImages.value.length > 0) {
    selectedImage.value = allImages.value[0]!
  }
}

function handleContact() {
  const element = document.getElementById('inquiry-form-section')
  if (element) {
    element.scrollIntoView({ behavior: 'smooth', block: 'start' })
  }
}

const inquiryForm = ref<InquiryForm>({
  name: '',
  email: '',
  phone: '',
  company: '',
  subject: '',
  message: ''
})

const isSubmitting = ref(false)

async function submitInquiry() {
  if (isSubmitting.value) return

  if (!inquiryForm.value.name || !inquiryForm.value.email || !inquiryForm.value.message) {
    ElMessage.warning('Please fill in all required fields')
    return
  }

  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
  if (!emailRegex.test(inquiryForm.value.email)) {
    ElMessage.warning('Please enter a valid email address')
    return
  }

  isSubmitting.value = true

  try {
    const response = await axios.post('/api/contact', {
      name: inquiryForm.value.name,
      email: inquiryForm.value.email,
      phone: inquiryForm.value.phone,
      company: inquiryForm.value.company,
      subject: inquiryForm.value.subject,
      message: inquiryForm.value.message,
      timestamp: Date.now()
    })

    if (response.data.code === 0) {
      ElMessage.success(response.data.data || 'Message sent successfully! We will get back to you soon.')
      inquiryForm.value = {
        name: '',
        email: '',
        phone: '',
        company: '',
        subject: '',
        message: ''
      }
    } else if (response.data.code === 40002) {
      ElMessage.warning(response.data.message || 'Duplicate request detected')
    } else {
      ElMessage.error(response.data.data || 'Failed to send message. Please try again.')
    }
  } catch (error) {
    console.error('Error submitting inquiry:', error)
    ElMessage.error('Failed to send message. Please try again later.')
  } finally {
    isSubmitting.value = false
  }
}

const relatedProducts = computed(() => {
  if (!currentProduct.value) return []
  return allProducts.value
    .filter(p =>
      p.category === currentProduct.value?.category &&
      p.id !== currentProduct.value?.id
    )
    .slice(0, 4)
})

async function goToProduct(id: number) {
  const product = await getProductById(id)
  const category = product?.category === 'tanker' ? 'truck' : product?.category
  router.push({
    path: `/${category}/${id}`
  })
}

function toggleMarkdown() {
  showMarkdown.value = !showMarkdown.value
}

const newProducts = ref<Product[]>([])
const allProducts = ref<Product[]>([])

async function loadProductsData() {
  allProducts.value = await getProducts()
  newProducts.value = await getNewProducts(5)
}

onMounted(async () => {
  await Promise.all([loadProductsData(), loadCurrentProduct(), loadCategoryTree()])
  if (currentProduct.value && currentProduct.value.subCategory) {
    currentCategory.value = currentProduct.value.subCategory
    updateExpandedKeys(currentProduct.value.subCategory)
  } else if (props.category) {
    currentCategory.value = props.category
  } else {
    const categoryParam = route.query.category as string
    if (categoryParam) {
      currentCategory.value = categoryParam
      updateExpandedKeys(categoryParam)
    }
  }
})

watch(() => props.category, (newCategory) => {
  if (newCategory && !currentProduct.value?.subCategory) {
    currentCategory.value = newCategory
    updateExpandedKeys(newCategory)
  }
})

watch(currentProduct, (newProduct) => {
  if (newProduct && newProduct.subCategory) {
    currentCategory.value = newProduct.subCategory
    updateExpandedKeys(newProduct.subCategory)
  }
})

watch(() => route.params, () => {
  if (props.id) {
    selectedImage.value = currentProduct.value?.image || ''
  }
})

function findCategoryLabel(nodes: TreeNode[], targetId: string): string {
  if (!targetId || targetId === 'all') {
    return 'All Products'
  }

  for (const node of nodes) {
    if (node.id === targetId) {
      return node.label
    }
    if (node.children && node.children.length > 0) {
      const found = findCategoryLabel(node.children, targetId)
      if (found && found !== 'All Products') {
        return found
      }
    }
  }
  return 'All Products'
}

const currentCategoryLabel = computed(() => {
  return findCategoryLabel(categoryTree.value, currentCategory.value)
})

const categoryTree = ref<CategoryTreeNode[]>([])

interface TreeNode {
  id: string
  label: string
  children?: TreeNode[]
}

async function loadCategoryTree() {
  categoryTree.value = await getCategoryTreeFromApi()
}

const isParentNode = computed(() => {
  return categoryTree.value.some(node => node.id === currentCategory.value && node.children && node.children.length > 0)
})

const updateExpandedKeys = (categoryId: string) => {
  if (categoryId === 'all') {
    expandedKeys.value = []
    return
  }

  const findParentNode = (nodes: TreeNode[], targetId: string): string | null => {
    for (const node of nodes) {
      if (node.children && node.children.length > 0) {
        const found = node.children.find(child => child.id === targetId)
        if (found) {
          return node.id
        }
        const parent = findParentNode(node.children, targetId)
        if (parent) {
          return parent
        }
      }
    }
    return null
  }

  if (isParentNode.value) {
    if (expandedKeys.value.includes(categoryId)) {
      expandedKeys.value = []
    } else {
      expandedKeys.value = [categoryId]
    }
  } else {
    const parentKey = findParentNode(categoryTree.value, categoryId)
    if (parentKey) {
      expandedKeys.value = [parentKey]
    } else {
      expandedKeys.value = []
    }
  }
}

function handleCategoryChange(categoryId: string) {
  currentCategory.value = categoryId
  updateExpandedKeys(categoryId)
}
</script>

<style scoped>
.products-page-container {
  position: relative;
  width: 100%;
  min-height: 100vh;
  background: #F6F5ED;
}

.floating-topbar {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  z-index: 100;
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

.products-main-section {
  padding: 40px 0 80px;
}

.products-main-inner {
  max-width: 1700px;
  margin: 0 auto;
  /* padding: 0 40px; */
}

.content-layout {
  display: grid;
  grid-template-columns: 370px 1fr;
  gap: 32px;
  align-items: start;
}

@media (max-width: 992px) {
  .content-layout {
    grid-template-columns: 1fr;
  }
}

/* .sidebar {
  position: sticky;
  top: 100px;
} */

@media (max-width: 992px) {
  .sidebar {
    position: static;
  }
}

.category-sidebar {
  background: #ffffff;
  /* border-radius: 16px; */
  padding: 18px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.06);
  border: 1px solid #e2e8f0;
}

.sidebar-search {
  margin-bottom: 16px;
}

.search-input-sidebar {
  width: 100%;
}

:deep(.search-input-sidebar .el-input__wrapper) {
  /* border-radius: 8px; */
  box-shadow: 0 1px 6px rgba(0, 0, 0, 0.04);
  border: 1px solid #e2e8f0;
  background: #f8fafc;
  transition: all 0.3s ease;
}

:deep(.search-input-sidebar .el-input__wrapper:hover) {
  border-color: #FF0000;
  background: #ffffff;
}

:deep(.search-input-sidebar .el-input__wrapper.is-focus) {
  border-color: #FF0000;
  background: #ffffff;
  box-shadow: 0 2px 8px rgba(255, 0, 0, 0.1);
}

:deep(.search-input-sidebar .el-input__inner) {
  font-size: 13px;
  padding: 8px 12px;
  height: 32px;
}

.sidebar-title {
  font-size: 20px;
  font-weight: 600;
  color: #1a2a4a;
  margin: 0 0 16px 0;
  position: relative;
  padding-bottom: 12px;
}

.sidebar-title::after {
  content: '';
  position: absolute;
  bottom: 0;
  left: 0;
  width: 40px;
  height: 3px;
  background: linear-gradient(90deg, #FF0000, transparent);
  border-radius: 2px;
}

.category-tree {
  background: #ffffff;
  /* border-radius: 12px; */
  padding: 16px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.06);
  border: 1px solid #e2e8f0;
}

:deep(.category-tree .el-tree-node__content) {
  padding: 8px 12px;
  /* border-radius: 8px; */
  transition: all 0.3s ease;
}

:deep(.category-tree .el-tree-node__content:hover) {
  background: transparent;
}

:deep(.category-tree .el-tree-node__content:hover .el-tree-node__label) {
  color: #FF0000 !important;
}

:deep(.category-tree .el-tree-node__content:hover .el-tree-node__expand-icon) {
  color: #FF0000 !important;
}

:deep(.category-tree .is-current > .el-tree-node__content:hover) {
  background: #FF0000 !important;
}

:deep(.category-tree .is-current > .el-tree-node__content:hover .el-tree-node__label) {
  color: #ffffff !important;
}

:deep(.category-tree .is-current > .el-tree-node__content:hover .el-tree-node__expand-icon) {
  color: #ffffff !important;
}

:deep(.category-tree .is-current > .el-tree-node__content) {
  background: #FF0000;
  color: #ffffff !important;
  font-weight: 600;
}

:deep(.category-tree .is-current > .el-tree-node__content .el-tree-node__label) {
  color: #ffffff !important;
}

/* :deep(.category-tree .el-tree-node__expand-icon) {
  color: #ffffff;
} */

:deep(.category-tree .el-tree-node__label) {
  font-size: 15px;
  white-space: normal;
  word-break: break-word;
  overflow: visible;
  line-height: 1.4;
}

.latest-products-sidebar {
  margin-top: 24px;
  padding-top: 24px;
  border-top: 1px solid #e2e8f0;
}

.latest-products-title {
  font-size: 18px;
  font-weight: 600;
  color: #1a2a4a;
  margin: 0 0 16px 0;
  position: relative;
  padding-bottom: 12px;
}

.latest-products-title::after {
  content: '';
  position: absolute;
  bottom: 0;
  left: 0;
  width: 40px;
  height: 3px;
  background: linear-gradient(90deg, #FF0000, transparent);
  border-radius: 2px;
}

.latest-products-list {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.latest-product-item {
  display: flex;
  align-items: center;
  padding: 12px;
  background: #f8fafc;
  border-radius: 8px;
  cursor: pointer;
  transition: all 0.3s ease;
}

.latest-product-item:hover {
  background: #fff5f5;
  transform: translateX(4px);
}

.latest-product-image {
  width: 60px;
  height: 60px;
  object-fit: cover;
  border-radius: 6px;
  flex-shrink: 0;
}

.latest-product-info {
  margin-left: 12px;
  flex: 1;
  min-width: 0;
}

.latest-product-name {
  font-size: 13px;
  font-weight: 500;
  color: #1a2a4a;
  margin: 0 0 4px 0;
  line-height: 1.4;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}

.latest-product-tag {
  font-size: 12px;
  color: #FF0000;
  margin: 0;
  font-weight: 500;
}


.product-detail {
  flex: 1;
  background: #ffffff;
  border-radius: 12px;
  padding: 32px;
  border: 1px solid #e2e8f0;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.06);
}

.detail-breadcrumb {
  margin-bottom: 24px;
}

:deep(.detail-breadcrumb .el-breadcrumb__inner) {
  color: #64748b !important;
  font-size: 14px;
  font-weight: 500;
  transition: all 0.3s ease;
}

:deep(.detail-breadcrumb .el-breadcrumb__inner:hover) {
  color: #FF0000 !important;
}

:deep(.detail-breadcrumb .el-breadcrumb__separator) {
  color: #cbd5e1 !important;
}

:deep(.detail-breadcrumb .el-breadcrumb__item:last-child .el-breadcrumb__inner) {
  color: #1a2a4a !important;
  font-weight: 600;
}

.product-detail-layout {
  display: flex;
  gap: 40px;
  align-items: flex-start;
  position: relative;
}

.product-image-section {
  flex: 0 0 480px;
  width: 480px;
  flex-shrink: 0;
}

.product-main-image {
  width: 480px;
  height: 360px;
  border-radius: 12px;
  overflow: hidden;
  background: #f8fafc;
  border: 1px solid #e2e8f0;
}

.product-main-image img {
  width: 100%;
  height: 100%;
  object-fit: contain;
}

.zoom-preview {
  position: fixed;
  width: 400px;
  height: 400px;
  border-radius: 12px;
  border: 1px solid #e2e8f0;
  background: #f8fafc;
  background-repeat: no-repeat;
  z-index: 9999;
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.2);
  overflow: hidden;
  pointer-events: none;
}

.product-thumbnails-wrapper {
  display: flex;
  align-items: center;
  gap: 12px;
  margin-top: 16px;
}

.thumbnail-nav-btn {
  flex-shrink: 0;
  width: 36px;
  height: 36px;
  border-radius: 50%;
  background: #ffffff;
  border: 1px solid #e2e8f0;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: all 0.3s ease;
  color: #64748b;
}

.thumbnail-nav-btn:hover {
  background: #FF0000;
  border-color: #FF0000;
  color: #ffffff;
}

.product-thumbnails {
  display: flex;
  gap: 12px;
  flex-wrap: wrap;
  flex: 1;
  justify-content: center;
}

.thumbnail {
  width: 80px;
  height: 60px;
  border-radius: 8px;
  overflow: hidden;
  cursor: pointer;
  border: 2px solid transparent;
  transition: all 0.3s ease;
  background: #f8fafc;
}

.thumbnail:hover {
  border-color: #FF0000;
}

.thumbnail.active {
  border-color: #FF0000;
  box-shadow: 0 0 0 2px rgba(255, 0, 0, 0.2);
}

.thumbnail img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.product-detail-info {
  flex: 1;
  min-width: 0;
}

.product-detail-title {
  font-size: 28px;
  font-weight: 700;
  color: #1a2a4a;
  margin: 0 0 12px 0;
  line-height: 1.3;
  display: flex;
  align-items: center;
  gap: 12px;
  flex-wrap: wrap;
}

.product-detail-tag {
  display: inline-block;
  padding: 6px 16px;
  background: #FF0000;
  color: #ffffff;
  font-size: 14px;
  font-weight: 600;
  border-radius: 20px;
}

.product-specs-text {
  background: #f8fafc;
  border-radius: 12px;
  padding: 20px 24px;
  margin-bottom: 24px;
  border: 1px solid #e2e8f0;
}

.product-specs-text p {
  font-size: 15px;
  color: #4a5568;
  margin: 0 0 10px 0;
  line-height: 1.6;
}

.product-specs-text p:last-child {
  margin-bottom: 0;
}

.product-detail-actions {
  display: flex;
  gap: 16px;
}

.product-detail-actions .el-button {
  padding: 12px 32px;
  font-size: 16px;
  font-weight: 600;
  display: flex;
  align-items: center;
  justify-content: center;
}

.product-detail-actions .el-button:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(255, 0, 0, 0.25);
}

@media (max-width: 1200px) {
  .product-detail-layout {
    flex-direction: column;
    gap: 24px;
  }

  .product-image-section {
    flex: 0 0 auto;
    width: 100%;
  }

  .product-main-image {
    height: 320px;
  }
}

.detail-section-title {
  font-size: 20px;
  font-weight: 600;
  color: #1a2a4a;
  margin: 0 0 16px 0;
  padding-bottom: 12px;
  border-bottom: 2px solid #FF0000;
  display: inline-block;
}

.product-detail-description {
  margin-bottom: 32px;
}

.description-text {
  font-size: 15px;
  color: #4a5568;
  line-height: 1.8;
  margin: 0;
}

.product-detail-features {
  margin-bottom: 32px;
}

.features-list {
  margin: 0;
  padding-left: 20px;
}

.features-list li {
  font-size: 15px;
  color: #4a5568;
  line-height: 1.8;
  margin-bottom: 8px;
}

.product-intro-title {
  font-size: 28px;
  font-weight: 700;
  color: #1a2a4a;
  margin: 32px 0 0 0;
  position: relative;
  padding: 0  16px;
  display: inline-block;
  width: 95%;
  border-bottom: 3px solid #FF0000;
  cursor: pointer;
  transition: all 0.3s ease;
}

.product-intro-title:hover {
  background-color: transparent;
}

.product-intro-title span {
  color: inherit;
}

.product-intro-title::before,
.product-intro-title::after {
  display: none;
}

.toggle-icon {
  position: absolute;
  right: 20px;
  top: 50%;
  transform: translateY(-50%);
  transition: all 0.3s ease;
  color: #FF0000;
}

.toggle-icon:hover {
  transform: translateY(-50%) scale(1.1);
}

.toggle-icon.collapsed {
  transform: translateY(-50%) rotate(-90deg);
}

.product-detail-markdown {
  margin-top: 40px;
  padding: 32px;
  background: #ffffff;
  border-radius: 12px;
  border: 1px solid #e2e8f0;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.05);
}

:deep(.product-detail-markdown h1) {
  font-size: 28px;
  font-weight: 700;
  color: #1a2a4a;
  margin: 0 0 24px 0;
  padding: 20px 24px;
  /* background: linear-gradient(135deg, #1a2a4a 0%, #2d3a5c 100%); */
  border-radius: 12px;
  position: relative;
  border-left: 5px solid #FF0000;
  box-shadow: 0 4px 12px rgba(26, 42, 74, 0.15);
}

:deep(.product-detail-markdown h1::before) {
  content: '';
  position: absolute;
  left: 0;
  top: 50%;
  transform: translateY(-50%);
  width: 8px;
  height: 24px;
  background: #FF0000;
  border-radius: 0 4px 4px 0;
}

:deep(.product-detail-markdown h1::after) {
  content: '';
  position: absolute;
  right: 24px;
  top: 50%;
  transform: translateY(-50%);
  width: 40px;
  height: 40px;
  background: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' fill='%23FF0000'%3E%3Cpath d='M12 2L2 7l10 5 10-5-10-5zM2 17l10 5 10-5M2 12l10 5 10-5'/%3E%3C/svg%3E") no-repeat center;
  opacity: 0.3;
}

:deep(.product-detail-markdown h2) {
  font-size: 22px;
  font-weight: 600;
  color: #1a2a4a;
  margin: 36px 0 18px 0;
  padding: 16px 20px;
  background: linear-gradient(135deg, #f8fafc 0%, #ffffff 100%);
  border-radius: 10px;
  position: relative;
  border-left: 4px solid #FF0000;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.04);
}

:deep(.product-detail-markdown h2::before) {
  content: '';
  position: absolute;
  left: 0;
  top: 50%;
  transform: translateY(-50%);
  width: 6px;
  height: 20px;
  background: #FF0000;
  border-radius: 0 3px 3px 0;
}

:deep(.product-detail-markdown h2::after) {
  content: '';
  position: absolute;
  right: 20px;
  top: 50%;
  transform: translateY(-50%);
  width: 24px;
  height: 24px;
  background: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' fill='%23FF0000'%3E%3Cpath d='M7 10l5 5 5-5z'/%3E%3C/svg%3E") no-repeat center;
  opacity: 0.4;
}

:deep(.product-detail-markdown h3) {
  font-size: 18px;
  font-weight: 600;
  color: #1a2a4a;
  margin: 28px 0 14px 0;
  padding: 12px 16px;
  background: #ffffff;
  border-radius: 8px;
  position: relative;
  border: 1px solid #e2e8f0;
  border-left: 3px solid #FF0000;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.03);
}

:deep(.product-detail-markdown h3::before) {
  content: '';
  position: absolute;
  left: 0;
  top: 50%;
  transform: translateY(-50%);
  width: 4px;
  height: 16px;
  background: #FF0000;
  border-radius: 0 2px 2px 0;
}

.product-detail-markdown p {
  font-size: 15px;
  color: #4a5568;
  line-height: 1.9;
  margin: 0 0 18px 0;
  text-align: justify;
}

.product-detail-markdown ul,
.product-detail-markdown ol {
  margin: 0 0 20px 0;
  padding-left: 28px;
}

.product-detail-markdown li {
  font-size: 15px;
  color: #4a5568;
  line-height: 1.9;
  margin-bottom: 10px;
  position: relative;
  padding-left: 8px;
}

.product-detail-markdown ul li::marker {
  color: #FF0000;
}

.product-detail-markdown ol li::marker {
  color: #FF0000;
  font-weight: 600;
}

:deep(.product-detail-markdown table) {
  width: 100%;
  border-collapse: collapse;
  margin: 24px 0;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
  border: 1px solid #e2e8f0 !important;
}

:deep(.product-detail-markdown table tr) {
  border: 1px solid #e2e8f0;
}

:deep(.product-detail-markdown table th),
:deep(.product-detail-markdown table td) {
  padding: 14px 18px;
  text-align: left;
  border: 1px solid #e2e8f0 !important;
  font-size: 14px;
  min-width: 100px;
  background: inherit !important;
}

:deep(.product-detail-markdown table th) {
  background: #FF0000 !important;
  font-weight: 600;
  color: #ffffff !important;
  text-transform: uppercase;
  letter-spacing: 0.5px;
  font-size: 13px;
}

:deep(.product-detail-markdown table tbody tr:nth-child(even)) {
  background: #fafafa;
}

:deep(.product-detail-markdown table tbody tr:hover),
:deep(.product-detail-markdown table tbody tr:nth-child(even):hover) {
  background: #f8fafc;
}

:deep(.product-detail-markdown table td) {
  color: #4a5568;
}

.product-detail-markdown img {
  max-width: 100%;
  height: auto;
  border-radius: 10px;
  margin: 20px 0;
  display: block;
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.08);
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.product-detail-markdown img:hover {
  transform: scale(1.01);
  box-shadow: 0 6px 24px rgba(0, 0, 0, 0.12);
}

.product-detail-markdown strong {
  font-weight: 600;
  color: #1a2a4a;
  background: linear-gradient(180deg, transparent 60%, rgba(255, 0, 0, 0.1) 60%);
}

.product-detail-markdown hr {
  border: none;
  border-top: 2px solid #f0f0f0;
  margin: 32px 0;
  position: relative;
}

.product-detail-markdown hr::before {
  content: '';
  position: absolute;
  left: 50%;
  top: -2px;
  transform: translateX(-50%);
  width: 60px;
  height: 2px;
  background: #FF0000;
}

.product-detail-markdown blockquote {
  margin: 20px 0;
  padding: 16px 20px;
  background: #f8fafc;
  border-left: 4px solid #FF0000;
  border-radius: 0 8px 8px 0;
  font-style: italic;
  color: #4a5568;
}

.product-detail-markdown code {
  background: #f0f0f0;
  padding: 2px 8px;
  border-radius: 4px;
  font-family: 'Consolas', 'Monaco', monospace;
  font-size: 14px;
  color: #e53e3e;
}

.product-detail-markdown pre {
  background: #1a2a4a;
  color: #e2e8f0;
  padding: 20px;
  border-radius: 10px;
  overflow-x: auto;
  margin: 20px 0;
}

.product-detail-markdown pre code {
  background: none;
  color: inherit;
  padding: 0;
}

.product-not-found {
  flex: 1;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 80px 40px;
  background: #ffffff;
  border-radius: 12px;
  border: 1px solid #e2e8f0;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.06);
  text-align: center;
}

.not-found-icon {
  color: #cbd5e1;
  margin-bottom: 24px;
}

.not-found-title {
  font-size: 24px;
  font-weight: 600;
  color: #1a2a4a;
  margin: 0 0 12px 0;
}

.not-found-desc {
  font-size: 16px;
  color: #4a5568;
  margin: 0;
  line-height: 1.6;
}

.related-products-section {
  margin: 48px 0 0;
  padding: 32px;
  background: #ffffff;
  border-radius: 16px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.06);
}

.related-title {
  font-size: 24px;
  font-weight: 600;
  color: #1a2a4a;
  margin: 0 0 24px 0;
  text-align: center;
  position: relative;
  padding: 0 60px;
  display: inline-block;
  width: 100%;
}

.related-title::before,
.related-title::after {
  content: '';
  position: absolute;
  top: 50%;
  width: 40px;
  height: 1px;
  background: linear-gradient(90deg, #FF0000, transparent);
}

.related-title::before {
  left: calc(50% - 200px);
  background: linear-gradient(90deg, #FF0000, transparent);
}

.related-title::after {
  right: calc(50% - 200px);
  background: linear-gradient(90deg, transparent, #FF0000);
}

.related-grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 20px;
}

.related-card {
  background: #ffffff;
  border: 1px solid #e2e8f0;
  border-radius: 12px;
  overflow: hidden;
  cursor: pointer;
  transition: all 0.3s ease;
}

.related-card:hover {
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
  transform: translateY(-4px);
  border-color: #FF0000;
}

.related-image {
  width: 100%;
  height: 160px;
  overflow: hidden;
  background: #f8fafc;
}

.related-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  transition: transform 0.3s ease;
}

.related-card:hover .related-image img {
  transform: scale(1.05);
}

.related-info {
  padding: 16px;
  text-align: center;
}

.related-name {
  font-size: 14px;
  font-weight: 600;
  color: #1a2a4a;
  margin: 0 0 6px 0;
  line-height: 1.4;
}

.related-category {
  font-size: 12px;
  color: #718096;
  margin: 0;
}

.inquiry-form-section {
  margin: 48px 0 0;
  padding: 32px;
  background: #ffffff;
  border-radius: 16px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.06);
}

.inquiry-title {
  font-size: 24px;
  font-weight: 600;
  color: #1a2a4a;
  margin: 0 0 8px 0;
  text-align: center;
}

.inquiry-subtitle {
  font-size: 14px;
  color: #4a5568;
  margin: 0 0 28px 0;
  text-align: center;
  line-height: 1.6;
}

.inquiry-form {
  margin-top: 24px;
}

.form-row {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 20px;
}

.form-item-half {
  flex: 1;
}

:deep(.inquiry-form .el-form-item__label) {
  font-weight: 500;
  color: #1a2a4a;
  padding-bottom: 6px;
}

:deep(.inquiry-form .el-input__wrapper),
:deep(.inquiry-form .el-textarea__wrapper) {
  border-radius: 8px;
  box-shadow: 0 1px 6px rgba(0, 0, 0, 0.04);
  border: 1px solid #e2e8f0;
  transition: all 0.3s ease;
}

:deep(.inquiry-form .el-input__wrapper) {
  height: 52px;
}

:deep(.inquiry-form .el-textarea__inner) {
  min-height: 120px !important;
  padding: 12px 16px;
}

:deep(.inquiry-form .el-input__inner) {
  height: 100%;
  font-size: 15px;
}

:deep(.inquiry-form .el-input__wrapper:hover),
:deep(.inquiry-form .el-textarea__wrapper:hover) {
  border-color: #FF0000;
}

:deep(.inquiry-form .el-input__wrapper.is-focus),
:deep(.inquiry-form .el-textarea__wrapper.is-focus) {
  border-color: #FF0000;
  box-shadow: 0 2px 8px rgba(255, 0, 0, 0.1);
}

.submit-btn {
  height: 44px;
  font-size: 15px;
  font-weight: 500;
  border-radius: 8px;
  background: linear-gradient(135deg, #FF0000 0%, #cc0000 100%);
  border: none;
  transition: all 0.3s ease;
}

.submit-btn:hover {
  background: linear-gradient(135deg, #cc0000 0%, #990000 100%);
  transform: translateY(-1px);
  box-shadow: 0 4px 12px rgba(255, 0, 0, 0.3);
}

@media (max-width: 768px) {
  .products-page-container {
    overflow-x: hidden !important;
  }

  .products-main-section {
    padding: 20px 0 40px;
    width: 100% !important;
    overflow-x: hidden !important;
  }

  .products-main-inner {
    padding: 0 16px !important;
    width: 100% !important;
    max-width: 100% !important;
    box-sizing: border-box !important;
    overflow-x: hidden !important;
  }

  .content-layout {
    grid-template-columns: 1fr !important;
    gap: 20px !important;
  }

  .sidebar {
    position: static !important;
  }

  .product-detail {
    padding: 16px !important;
    border-radius: 8px !important;
    width: 100% !important;
    max-width: 100% !important;
    min-width: 0 !important;
    box-sizing: border-box !important;
    overflow-x: hidden !important;
  }

  .detail-breadcrumb {
    margin-bottom: 16px !important;
    overflow-x: auto;
    white-space: nowrap;
  }

  :deep(.detail-breadcrumb .el-breadcrumb__inner) {
    font-size: 12px !important;
  }

  .product-detail-layout {
    flex-direction: column !important;
    gap: 16px !important;
  }

  .product-image-section {
    width: 100% !important;
    flex: none !important;
  }

  .product-main-image {
    width: 100% !important;
    height: 260px !important;
    border-radius: 8px !important;
  }

  .zoom-preview {
    width: 280px !important;
    height: 280px !important;
  }

  .product-thumbnails-wrapper {
    padding: 0 !important;
  }

  .product-thumbnails {
    gap: 8px !important;
  }

  .product-thumbnail {
    width: 60px !important;
    height: 60px !important;
    border-radius: 6px !important;
  }

  .product-detail-info {
    flex: none !important;
    width: 100% !important;
  }

  .product-detail-title {
    font-size: 20px !important;
    margin-bottom: 10px !important;
    gap: 8px !important;
  }

  .product-detail-tag {
    font-size: 11px !important;
    padding: 4px 12px !important;
  }

  .product-specs-text {
    padding: 14px 16px !important;
    margin-bottom: 16px !important;
    border-radius: 8px !important;
  }

  :deep(.product-specs-text p) {
    font-size: 13px !important;
    line-height: 1.6 !important;
    margin-bottom: 8px !important;
  }

  .product-detail-actions {
    flex-direction: column !important;
    gap: 10px !important;
  }

  .product-detail-actions .el-button {
    width: 100% !important;
    padding: 12px 20px !important;
    font-size: 14px !important;
  }

  .product-detail-description {
    margin-top: 20px !important;
  }

  .product-detail-description h3 {
    font-size: 18px !important;
    margin-bottom: 12px !important;
  }

  .product-detail-description p {
    font-size: 14px !important;
    line-height: 1.7 !important;
  }

  .product-detail-features {
    margin-top: 20px !important;
  }

  .product-detail-features h3 {
    font-size: 18px !important;
    margin-bottom: 12px !important;
  }

  .feature-list {
    gap: 10px !important;
  }

  .feature-item {
    padding: 12px 14px !important;
    font-size: 13px !important;
    border-radius: 8px !important;
  }

  .product-detail-markdown {
    margin-top: 20px !important;
    padding: 16px !important;
    border-radius: 8px !important;
    overflow-x: hidden !important;
  }

  :deep(.product-detail-markdown h1) {
    font-size: 20px !important;
    margin-bottom: 16px !important;
  }

  :deep(.product-detail-markdown h2) {
    font-size: 17px !important;
    margin: 20px 0 12px 0 !important;
  }

  :deep(.product-detail-markdown h3) {
    font-size: 15px !important;
    margin: 16px 0 10px 0 !important;
  }

  :deep(.product-detail-markdown p) {
    font-size: 14px !important;
    line-height: 1.7 !important;
    margin-bottom: 12px !important;
  }

  :deep(.product-detail-markdown li) {
    font-size: 14px !important;
    line-height: 1.7 !important;
    margin-bottom: 8px !important;
  }

  :deep(.product-detail-markdown img) {
    max-width: 100% !important;
    height: auto !important;
    border-radius: 6px !important;
  }

  .inquiry-form-section {
    padding: 20px !important;
    margin: 32px 16px 0 !important;
    border-radius: 8px !important;
  }

  .inquiry-title {
    font-size: 18px !important;
    margin-bottom: 6px !important;
  }

  .inquiry-subtitle {
    font-size: 13px !important;
    margin-bottom: 16px !important;
    line-height: 1.5 !important;
  }

  .inquiry-form-section h3 {
    font-size: 20px !important;
    margin-bottom: 8px !important;
  }

  .inquiry-form-section p {
    font-size: 13px !important;
    margin-bottom: 20px !important;
  }

  .form-row {
    grid-template-columns: 1fr !important;
    gap: 0 !important;
  }

  .form-item-half {
    flex: 1 !important;
  }

  .form-item-full {
    grid-column: 1 !important;
  }

  :deep(.el-form-item__label) {
    font-size: 13px !important;
    padding-bottom: 4px !important;
  }

  :deep(.el-input__inner) {
    font-size: 14px !important;
    height: 42px !important;
    border-radius: 8px !important;
  }

  :deep(.el-textarea__inner) {
    font-size: 14px !important;
    border-radius: 8px !important;
    min-height: 100px !important;
  }

  .form-buttons {
    flex-direction: column !important;
    gap: 10px !important;
    margin-top: 20px !important;
  }

  .form-buttons .el-button {
    width: 100% !important;
    padding: 12px 20px !important;
    font-size: 14px !important;
  }

  .related-products-section {
    margin: 32px 0 0 !important;
    padding: 20px !important;
    border-radius: 8px !important;
  }

  .related-title {
    font-size: 18px !important;
    margin-bottom: 16px !important;
    padding: 0 !important;
  }

  .related-title::before,
  .related-title::after {
    display: none !important;
  }

  .related-grid {
    grid-template-columns: repeat(2, 1fr) !important;
    gap: 12px !important;
  }

  .related-image {
    height: 120px !important;
  }

  .related-info {
    padding: 12px !important;
  }

  .related-name {
    font-size: 13px !important;
    margin-bottom: 6px !important;
  }

  .related-desc {
    font-size: 11px !important;
  }
}
</style>
