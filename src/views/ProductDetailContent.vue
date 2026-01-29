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
            :category-tree="categoryTree"
            :current-category="currentCategory"
            :latest-products="latestProducts"
            @update:searchQuery="searchQuery = $event"
            @category-change="handleCategoryChange"
          />

          <div class="product-detail" v-if="currentProduct">
            <el-breadcrumb separator="/" class="detail-breadcrumb">
              <el-breadcrumb-item :to="{ path: '/' }">Home</el-breadcrumb-item>
              <el-breadcrumb-item :to="{ path: '/products' }">Products</el-breadcrumb-item>
              <el-breadcrumb-item v-if="currentCategoryLabel && currentCategoryLabel !== 'All Products'">
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

            <div class="product-detail-markdown" v-html="renderedMarkdown"></div>
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
  </div>
</template>

<script setup lang="ts">
import TopBar from '@/components/TopBar.vue'
import Footer from '@/components/Footer.vue'
import ContactFixed from '@/components/ContactFixed.vue'
import Sidebar from '@/components/Sidebar.vue'
import { useRoute } from 'vue-router'
import { ref, computed, onMounted, watch } from 'vue'
import { marked } from 'marked'
import { products, getProductById } from '@/data/products'

interface Props {
  id?: number
  category?: string
}

const props = defineProps<Props>()

const route = useRoute()

const searchQuery = ref('')
const currentCategory = ref<string>('all')
const expandedKeys = ref<string[]>([])

const currentProductId = computed(() => {
  if (props.id) {
    return props.id
  }
  const id = route.query.id as string
  return id ? parseInt(id) : null
})

const currentProduct = computed(() => {
  if (currentProductId.value) {
    return getProductById(currentProductId.value)
  }
  return null
})

const selectedImage = ref('')

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
      markdownContent.value = marked.parse(text) as string
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
  console.log('Contact clicked')
}

onMounted(() => {
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
  return findCategoryLabel(categoryTree, currentCategory.value)
})

interface TreeNode {
  id: string
  label: string
  children?: TreeNode[]
}

const categoryTree: TreeNode[] = [
{
    id: 'all',
    label: 'All Products',
    children: []
  },
  {
    id: 'LiquidandPowerTransportTrailers',
    label: 'Liquid and Power Transport Trailers',
    children: [
      { id: 'AluminiumFuelTanker', label: 'Aluminium Fuel Tanker' },
      { id: 'CarbonSteelFuelTank Trailer', label: 'Carbon Steel Fuel Tank Trailer' },
      { id: 'BulkCementTrailer', label: 'Bulk Cement Trailer' },
      { id: 'AsphaltTankTrailers', label: 'Asphalt Tank Trailers' },
      { id: 'GasTankerTrailer', label: 'Gas Tanker Trailer' },
      { id: 'StainlessSteelTanker Trailer', label: 'Stainless Steel Tanker Trailer' },
      { id: 'ChemicalTankTrailer', label: 'Chemical Tank Trailer' },
      { id: 'StorageTank', label: 'Storage Tank' },
    ]
  },
  {
    id: 'ContainerSemiTrailer',
    label: 'Container Semi Trailer',
    children: [
      { id: 'SkeletalTrailer', label: 'Skeletal Trailer' },
      { id: 'FlatbedTrailer', label: 'Flatbed Trailer' },
    ]
  },
  {
    id: 'SemiTrailer',
    label: 'Semi Trailer',
    children: [
      { id: 'LowbedSemiTrailer', label: 'Lowbed Semi Trailer' },
      { id: 'TipperSemiTrailer', label: 'Tipper Semi Trailer' },
      { id: 'FenceCargoTrailer', label: 'Fence Cargo Trailer' },
      { id: 'SideWallTipper', label: 'Side Wall Tipper' },
      { id: 'SideCurtainTrailer', label: 'Side Curtain Trailer' },
	  { id: 'CarCarrierTailer', label: 'Car Carrier Tailer' },
	  { id: 'BoxSemiTrailer', label: 'Box Semi Trailer' },
	  { id: 'FullDrawbarTrailer', label: 'Full Drawbar Trailer' },
	  { id: 'RemovableGooseneckTrailer', label: 'Removable Gooseneck Trailer' },
    ]
  },
  {
    id: 'ShacmanTrucks',
    label: 'Shacman Trucks',
    children: [
      { id: 'ShacmanDumpTruck', label: 'Shacman Dump Truck' },
      { id: 'ShacmanTractorTruck', label: 'Shacman Tractor Truck' },
      { id: 'ShacmanTankerTrucks', label: 'Shacman Tanker Trucks' },
    ]
  },
  {
    id: 'Accessories',
    label: 'Accessories',
    children: [
      { id: 'TrailerAccessories', label: 'Trailer Accessories' },
      { id: 'Engine', label: 'Engine' },
    ]
  },
  {
    id: 'SinotruckHowo',
    label: 'Sinotruck Howo',
    children: [
      { id: 'HOWOTractorTruck', label: 'HOWO Tractor Truck' },
	  { id: 'HOWODumpTruck', label: 'HOWO Dump Truck' },
	  { id: 'HOWOTankerTruck', label: 'HOWO Tanker Truck' },
    ]
  },
  {
    id: 'ExistingTrucksAndTrailers',
    label: 'Existing Trucks and Trailers',
    children: [
      { id: 'Trailer', label: 'Trailer' },
    ]
  },
  {
    id: 'excavator',
    label: 'Excavator',
    children: [

    ]
  }
]

function updateExpandedKeys(categoryId: string) {
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

  const isParentNode = categoryTree.some(node => node.id === categoryId && node.children && node.children.length > 0)

  if (isParentNode) {
    // 点击的是父菜单，展开自己
    if (expandedKeys.value.includes(categoryId)) {
      // 如果已经展开，则收起
      expandedKeys.value = []
    } else {
      // 否则只展开自己
      expandedKeys.value = [categoryId]
    }
  } else {
    // 点击的是子菜单，展开父菜单
    const parentKey = findParentNode(categoryTree, categoryId)
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

const latestProducts = computed(() => {
  return products.slice(0, 4)
})
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

.sidebar {
  position: sticky;
  top: 100px;
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

.product-detail-markdown {
  margin-top: 40px;
  padding: 32px;
  background: #ffffff;
  border-radius: 12px;
  border: 1px solid #e2e8f0;
}

.product-detail-markdown h1 {
  font-size: 28px;
  font-weight: 700;
  color: #1a2a4a;
  margin: 0 0 24px 0;
  padding-bottom: 16px;
  border-bottom: 2px solid #FF0000;
}

.product-detail-markdown h2 {
  font-size: 22px;
  font-weight: 600;
  color: #1a2a4a;
  margin: 32px 0 16px 0;
  padding-bottom: 8px;
  border-bottom: 1px solid #e2e8f0;
}

.product-detail-markdown h3 {
  font-size: 18px;
  font-weight: 600;
  color: #1a2a4a;
  margin: 24px 0 12px 0;
}

.product-detail-markdown p {
  font-size: 15px;
  color: #4a5568;
  line-height: 1.8;
  margin: 0 0 16px 0;
}

.product-detail-markdown ul,
.product-detail-markdown ol {
  margin: 0 0 16px 0;
  padding-left: 24px;
}

.product-detail-markdown li {
  font-size: 15px;
  color: #4a5568;
  line-height: 1.8;
  margin-bottom: 8px;
}

.product-detail-markdown table {
  width: 100%;
  border-collapse: collapse;
  margin: 16px 0;
}

.product-detail-markdown th,
.product-detail-markdown td {
  padding: 12px 16px;
  text-align: left;
  border: 1px solid #e2e8f0;
  font-size: 14px;
}

.product-detail-markdown th {
  background: #f8fafc;
  font-weight: 600;
  color: #1a2a4a;
}

.product-detail-markdown td {
  color: #4a5568;
}

.product-detail-markdown img {
  max-width: 100%;
  height: auto;
  border-radius: 8px;
  margin: 16px 0;
  display: block;
}

.product-detail-markdown strong {
  font-weight: 600;
  color: #1a2a4a;
}

.product-detail-markdown hr {
  border: none;
  border-top: 1px solid #e2e8f0;
  margin: 24px 0;
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
</style>
