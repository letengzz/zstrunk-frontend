<!-- eslint-disable @typescript-eslint/no-explicit-any -->
<!-- eslint-disable @typescript-eslint/no-explicit-any -->
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
            :current-category="highlightedCategory"
            :expanded-keys="expandedKeys"
            @update:searchQuery="searchQuery = $event"
            @category-change="handleCategoryChange"
            @subcategories-change="handleSubcategoriesChange"
          />

          <div class="products-content">
            <div class="category-header">
              <h2 class="category-title">{{ currentCategoryLabel }}</h2>
              <p class="category-slogan">Your Professional Aluminium Fuel Tanker Supplier</p>
              <p class="category-subtitle">ZhiShun Semi-trailer Company is a leading enterprise specializing in the production and sales of various tank semi-trailers, dedicated to delivering high-quality and high-performance transportation solutions to global customers.</p>
              <div class="feature-boxes">
                <div class="feature-box">
                  <div class="feature-icon">
                    <div class="i-ep-money" w28 h28></div>
                  </div>
                  <div class="feature-content">
                    <h4 class="feature-title">Advanced Equipment</h4>
                    <p class="feature-desc">Equipped with state-of-the-art production facilities and modern production lines, complemented by advanced customized manufacturing technology, the company ensures both the efficiency and superior quality of its products.</p>
                  </div>
                </div>
                <div class="feature-box">
                  <div class="feature-icon">
                    <div class="i-ep-tools" w28 h28></div>
                  </div>
                  <div class="feature-content">
                    <h4 class="feature-title">Competitive Pricing</h4>
                    <p class="feature-desc">With a professional procurement and costing team, we strive to optimize costs while ensuring profitability, thereby offering highly competitive pricing to our valued customers.</p>
                  </div>
                </div>
                <div class="feature-box">
                  <div class="feature-icon">
                    <div class="i-ep-setting" w28 h28></div>
                  </div>
                  <div class="feature-content">
                    <h4 class="feature-title">Customized Service</h4>
                    <p class="feature-desc">Upon receiving your requirements, our experienced engineering team will promptly provide you with comprehensive and optimized customized solutions tailored to your specific needs.</p>
                  </div>
                </div>
                <div class="feature-box">
                  <div class="feature-icon">
                    <div class="i-ep-box" w28 h28></div>
                  </div>
                  <div class="feature-content">
                    <h4 class="feature-title">Quality Control</h4>
                    <p class="feature-desc">In terms of quality assurance, the company adheres strictly to industry standards and norms, utilizing cutting-edge testing equipment to ensure consistent product quality and maintain an excellent reputation in the market.</p>
                  </div>
                </div>
              </div>
            </div>

            <div class="products-grid" v-if="paginatedProducts.length > 0">
              <div class="product-col" v-for="product in paginatedProducts" :key="product.id">
                <el-card class="product-card" :body-style="{ padding: '0px', height: '100%' }" :style="{ cursor: 'pointer' }">
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

            <div class="pagination-section" v-if="filteredProducts.length > 0">
              <el-pagination
                v-model:current-page="currentPage"
                v-model:page-size="pageSize"
                :page-sizes="[6, 12, 18, 24]"
                :layout="paginationLayout"
                :total="filteredProducts.length"
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                class="pagination"
              />
            </div>

            <div class="category-markdown" v-if="renderedMarkdown">
              <div v-html="renderedMarkdown"></div>
            </div>


          </div>
        </div>
      </div>
    </div>

    <Footer />

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
import Sidebar from '@/components/Sidebar.vue'
import { useRouter, useRoute } from 'vue-router'
import { ref, computed, onMounted, watch } from 'vue'
import { getProductById, getProductsGroupedByCategoryFromApi, getNewProducts, getCategoryTreeFromApi, type Product, type CategoryTreeNode } from '@/data/products'
import { marked } from 'marked'
import { useProductsStore } from '@/stores/modules/products'

const router = useRouter()
const route = useRoute()
const productsStore = useProductsStore()

const searchQuery = ref('')
const currentCategory = ref<string>('all')
const currentCategories = ref<string[]>([])
const highlightedCategory = ref<string>('all')
const currentPage = ref(1)
const pageSize = ref(6)
const expandedKeys = ref<string[]>([])
const categoryProductCounts = ref<Record<string, number>>({})
const isLoadingCategories = ref(true)
const paginationLayout = ref('total, sizes, prev, pager, next')
const categoryTree = ref<CategoryTreeNode[]>([])

onUnmounted(() => {
  window.removeEventListener('resize', updatePaginationLayout)
})

function updatePaginationLayout() {
  if (window.innerWidth < 576) {
    paginationLayout.value = 'prev, pager, next'
  } else {
    paginationLayout.value = 'total, sizes, prev, pager, next, jumper'
  }
}

const productsList = computed(() => productsStore.products)

async function loadProducts() {
  await productsStore.loadProducts()
}

async function loadCategories() {
  try {
    isLoadingCategories.value = true
    const groupedProducts = await getProductsGroupedByCategoryFromApi()
    categoryProductCounts.value = {}
    for (const [category, products] of Object.entries(groupedProducts)) {
      categoryProductCounts.value[category] = products.length
    }
    categoryTree.value = await getCategoryTreeFromApi()
  } catch (error) {
    console.error('Error loading categories:', error)
  } finally {
    isLoadingCategories.value = false
  }
}

onMounted(async () => {
  updatePaginationLayout()
  window.addEventListener('resize', updatePaginationLayout)

  await Promise.all([loadProducts(), loadCategories(), loadNewProducts()])

  const categoryParam = route.query.category as string
  const categoriesParam = route.query.categories as string

  if (categoriesParam && categoryParam) {
    const ids = categoriesParam.split(',')
    currentCategories.value = ids
    currentCategory.value = ids[0] || 'all'
    highlightedCategory.value = categoryParam || 'all'
    expandedKeys.value = [categoryParam]
  } else if (categoriesParam) {
    const ids = categoriesParam.split(',')
    currentCategories.value = ids
    currentCategory.value = ids[0] || 'all'
    highlightedCategory.value = ids[0] || 'all'
    expandedKeys.value = ids
  } else if (categoryParam) {
    currentCategory.value = categoryParam
    highlightedCategory.value = categoryParam
    updateExpandedKeys(categoryParam)
  }

  await loadMarkdown()
})

watch(() => [route.query.category, route.query.categories], ([newCategory, newCategories]) => {
  if (newCategories && newCategory) {
    const ids = (newCategories as string).split(',')
    currentCategories.value = ids
    currentCategory.value = ids[0] || 'all'
    highlightedCategory.value = newCategory as string
    expandedKeys.value = [newCategory as string]
    currentPage.value = 1
  } else if (newCategories) {
    const ids = (newCategories as string).split(',')
    currentCategories.value = ids
    currentCategory.value = ids[0] || 'all'
    highlightedCategory.value = ids[0] || 'all'
    expandedKeys.value = ids
    currentPage.value = 1
  } else if (newCategory) {
    currentCategories.value = []
    currentCategory.value = newCategory as string
    highlightedCategory.value = newCategory as string
    updateExpandedKeys(newCategory as string)
    currentPage.value = 1
  } else {
    currentCategories.value = []
    currentCategory.value = 'all'
    highlightedCategory.value = 'all'
    updateExpandedKeys('all')
  }
})

interface TreeNode {
  id: string
  label: string
  children?: TreeNode[]
  markdownPath?: string
}

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

  const isParentNode = categoryTree.value.some(node => node.id === categoryId && node.children && node.children.length > 0)

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
    const parentKey = findParentNode(categoryTree.value, categoryId)
    if (parentKey) {
      expandedKeys.value = [parentKey]
    } else {
      expandedKeys.value = []
    }
  }
}

const mainCategoryMap: Record<string, 'truck' | 'excavator'> = {
  'LiquidandPowerTransportTrailers': 'truck',
  'ContainerSemiTrailer': 'truck',
  'SemiTrailer': 'truck',
  'ShacmanTrucks': 'truck',
  'Accessories': 'truck',
  'ExistingTrucksAndTrailers': 'truck',
  'excavator': 'excavator'
}

const isMainCategory = (categoryId: string): boolean => {
  return categoryId in mainCategoryMap
}

const filteredProducts = computed(() => {
  let result = [...productsList.value]

  if (currentCategories.value.length > 0) {
    result = result.filter(product => currentCategories.value.includes(product.subCategory))
  } else if (currentCategory.value !== 'all') {
    if (isMainCategory(currentCategory.value)) {
      const categoryType = mainCategoryMap[currentCategory.value]
      if (categoryType === 'truck') {
        result = result.filter(product => product.category === 'truck')
      } else if (categoryType === 'excavator') {
        result = result.filter(product => product.category === 'excavator')
      }
    } else {
      result = result.filter(product => product.subCategory === currentCategory.value)
    }
  }

  if (searchQuery.value) {
    const query = searchQuery.value.toLowerCase()
    result = result.filter(product =>
      product.name.toLowerCase().includes(query) ||
      product.desc.toLowerCase().includes(query)
    )
  }

  return result
})

const paginatedProducts = computed(() => {
  const start = (currentPage.value - 1) * pageSize.value
  const end = start + pageSize.value
  return filteredProducts.value.slice(start, end)
})

function handleCategoryChange(categoryId: string) {
  currentCategory.value = categoryId
  currentPage.value = 1
  updateExpandedKeys(categoryId)
}

function handleSubcategoriesChange(subcategoryIds: string[]) {
  currentCategories.value = subcategoryIds
  currentPage.value = 1
}

function handleSizeChange(size: number) {
  pageSize.value = size
  currentPage.value = 1
}

function handleCurrentChange(page: number) {
  currentPage.value = page
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

// function findCategoryLabel(nodes: TreeNode[], targetId: string): string {
//   if (!targetId || targetId === 'all') {
//     return 'All Products'
//   }

//   for (const node of nodes) {
//     if (node.id === targetId) {
//       return node.label
//     }
//     if (node.children && node.children.length > 0) {
//       const found = findCategoryLabel(node.children, targetId)
//       if (found && found !== 'All Products') {
//         return found
//       }
//     }
//   }
//   return 'All Products'
// }

const currentCategoryLabel = computed(() => {
  if (highlightedCategory.value !== 'all') {
    const node = findCategoryNode(categoryTree.value, highlightedCategory.value)
    if (node) {
      if (node.children && node.children.length > 0) {
        return node.label
      }
    }
  }

  if (currentCategories.value.length > 0) {
    const firstCategory = currentCategories.value[0]
    if (firstCategory) {
      const node = findCategoryNode(categoryTree.value, firstCategory)
      return node ? node.label : 'Products'
    }
    return 'Products'
  }

  if (currentCategory.value !== 'all') {
    const node = findCategoryNode(categoryTree.value, currentCategory.value)
    return node ? node.label : 'Products'
  }

  return 'All Products'
})

function findCategoryNode(nodes: TreeNode[], id: string): TreeNode | null {
  for (const node of nodes) {
    if (node.id === id) return node
    if (node.children) {
      const found = findCategoryNode(node.children, id)
      if (found) return found
    }
  }
  return null
}

const newProducts = ref<Product[]>([])

async function loadNewProducts() {
  newProducts.value = await getNewProducts(5)
}

const currentCategoryNode = computed(() => {
  // 如果有 categories 参数
  if (currentCategories.value.length > 0) {
    // 检查 highlightedCategory 是不是父分类
    if (highlightedCategory.value !== 'all') {
      const node = findCategoryNode(categoryTree.value, highlightedCategory.value)
      if (node && node.children && node.children.length > 0) {
        // highlightedCategory 是父分类，检查子分类是否有 markdown
        const firstSubCategory = currentCategories.value[0]
        if (firstSubCategory) {
          const subNode = findCategoryNode(categoryTree.value, firstSubCategory)
          if (subNode && subNode.markdownPath) {
            return subNode
          }
        }
        return null
      }
    }
  }

  // 其他情况查找当前分类的节点
  return findCategoryNode(categoryTree.value, currentCategory.value)
})

const markdownContent = ref('')
const renderedMarkdown = computed(() => markdownContent.value)

async function loadMarkdown() {
  try {
    const categoryNode = currentCategoryNode.value
    console.log('loadMarkdown called:', {
      currentCategory: currentCategory.value,
      categoryTreeLength: categoryTree.value.length,
      categoryNode: categoryNode
    })
    if (!categoryNode?.markdownPath) {
      markdownContent.value = ''
      console.log('No markdownPath found, skipping')
      return
    }

    const response = await fetch(categoryNode.markdownPath)
    if (response.ok) {
      const text = await response.text()
      let html = marked.parse(text) as string
      html = html.replace(/<table([^>]*)style="[^"]*"/gi, '<table$1')
      html = html.replace(/<th([^>]*)style="[^"]*"/gi, '<th$1')
      html = html.replace(/<td([^>]*)style="[^"]*"/gi, '<td$1')
      html = html.replace(/<tr([^>]*)style="[^"]*"/gi, '<tr$1')
      html = html.replace(/<img\s+/gi, '<img loading="lazy" ')
      markdownContent.value = html
      console.log('Markdown loaded successfully')
    }
  } catch (error) {
    console.error('Failed to load markdown:', error)
    markdownContent.value = ''
  }
}

watch(currentCategory, async () => {
  await loadMarkdown()
}, { immediate: true })

watch(categoryTree, async () => {
  console.log('categoryTree changed:', JSON.stringify(categoryTree.value, null, 2))
  await loadMarkdown()
}, { deep: true })

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
  background: url('/images/product/Top/image.png') center/cover no-repeat;
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
  padding: 0 40px;
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

.products-content {
  flex: 1;
}

.category-header {
  margin-bottom: 24px;
  /* padding: 0px 24px; */
  font-size: 26px;
  /* background: #ffffff; */
  /* border-radius: 12px; */
  /* border: 1px solid #e2e8f0; */
  /* box-shadow: 0 2px 12px rgba(0, 0, 0, 0.06); */
}

.category-title {
  font-size: 24px;
  font-weight: 600;
  color: #1a2a4a;
  margin: 0 0 12px 0;
  position: relative;
  display: inline-block;
}

.category-title::after {
  content: '';
  position: absolute;
  bottom: -4px;
  left: 0;
  width: 60px;
  height: 3px;
  background: linear-gradient(90deg, #FF0000, transparent);
  border-radius: 2px;
}

.category-slogan {
  font-size: 24px;
  color: #1a2a4a;
  margin: 16px 0;
  text-align: center;
  font-weight: 600;
}

.category-subtitle {
  font-size: 14px;
  color: #555555;
  margin: 0;
  line-height: 1.6;
  text-align: center;
}

.feature-boxes {
  display: grid;
  grid-template-columns: repeat(2, 0.6fr);
  gap: 24px;
  margin-top: 32px;
  box-sizing: border-box;
  padding-bottom: 32px;
  border-bottom: 2px dashed #FF0000;
}

.feature-box {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
  box-sizing: border-box;
  padding: 32px 28px;
  min-height: 200px;
  background-color: #fff;
  border-radius: 10px;
  border-bottom: 3px solid #FF0000;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.08);
  transition: all 0.3s ease;
}

.feature-box:hover {
  box-shadow: 0 8px 30px rgba(255, 0, 0, 0.15);
  transform: translateY(-4px);
}

.feature-icon {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 50px;
  height: 50px;
  background: linear-gradient(135deg, #FF0000 0%, #cc0000 100%);
  border-radius: 8px;
  color: #ffffff;
  flex-shrink: 0;
  margin: 0 0 16px 0;
}

.feature-content {
  flex: 1;
}

.feature-title {
  font-size: 18px;
  font-weight: 600;
  color: #1a2a4a;
  margin: 0 0 10px 0;
}

.feature-desc {
  font-size: 14px;
  color: #666666;
  margin: 0;
  line-height: 1.6;
}

.products-grid {
  display: flex;
  flex-wrap: wrap;
  gap: 24px;
  justify-content: flex-start;
}

.product-col {
  width: calc(33.333% - 16px);
}

.pagination-section {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 40px 0;
  /* background: #ffffff;
  border-radius: 16px;
  border: 1px solid #e2e8f0;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.06); */
  margin-top: 40px;
}

:deep(.pagination .el-pagination) {
  display: flex;
  justify-content: center;
  align-items: center;
}

:deep(.pagination .el-pager li) {
  /* border-radius: 8px; */
  margin: 0 4px;
  font-weight: 500;
  transition: all 0.3s ease;
}

:deep(.pagination .el-pager li:hover) {
  background: #FF0000;
  color: #ffffff;
}

:deep(.pagination .el-pager li.is-active) {
  background: #FF0000;
  color: #ffffff;
  font-weight: 600;
}

:deep(.pagination .btn-prev),
:deep(.pagination .btn-next) {
  border-radius: 8px;
  transition: all 0.3s ease;
}

:deep(.pagination .btn-prev:hover),
:deep(.pagination .btn-next:hover) {
  background: #FF0000;
  color: #ffffff;
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
  .products-page-container {
    overflow-x: hidden !important;
  }

  .products-main-section {
    padding: 40px 0 60px;
  }

  .products-main-inner {
    padding: 0 20px !important;
  }

  .content-layout {
    grid-template-columns: 1fr !important;
    gap: 24px !important;
  }

  .products-content {
    width: 100% !important;
    min-width: 0;
    overflow-x: hidden;
  }

  .category-sidebar {
    padding: 20px !important;
  }

  .category-header {
    margin-bottom: 24px;
    text-align: center;
  }

  .category-title {
    font-size: 22px;
    margin-bottom: 10px;
  }

  .category-title::after {
    left: 50%;
    transform: translateX(-50%);
    width: 50px;
  }

  .category-slogan {
    font-size: 18px;
    margin: 12px 0;
  }

  .category-subtitle {
    font-size: 14px;
    line-height: 1.6;
    text-align: center;
  }

  .feature-boxes {
    grid-template-columns: 1fr !important;
    gap: 16px !important;
    margin-top: 24px !important;
  }

  .feature-box {
    padding: 20px !important;
    flex-direction: row !important;
    align-items: flex-start !important;
    text-align: left !important;
  }

  .feature-icon {
    width: 44px !important;
    height: 44px !important;
    margin-right: 14px !important;
    margin-bottom: 0 !important;
  }

  .feature-content {
    flex: 1 !important;
  }

  .feature-title {
    font-size: 16px !important;
    margin-bottom: 8px !important;
  }

  .feature-desc {
    font-size: 13px !important;
    line-height: 1.6 !important;
  }

  .products-grid {
    gap: 16px !important;
  }

  .product-col {
    width: calc(50% - 8px) !important;
  }

  .product-card {
    min-height: 240px !important;
  }

  .product-image {
    height: 140px !important;
    margin: 14px auto 0 !important;
  }

  .product-tag {
    top: 8px !important;
    right: 8px !important;
    padding: 5px 12px !important;
    font-size: 11px !important;
  }

  .product-content {
    padding: 14px !important;
  }

  .product-name {
    font-size: 14px !important;
    margin-bottom: 8px !important;
  }

  .product-footer .el-button {
    font-size: 13px !important;
    padding: 8px 16px !important;
  }

  .category-markdown {
    padding: 24px !important;
    margin-top: 30px !important;
    width: 100% !important;
    max-width: 100% !important;
    box-sizing: border-box !important;
    overflow-x: hidden !important;
  }

  :deep(.category-markdown h1) {
    font-size: 24px !important;
    margin-bottom: 24px !important;
  }

  :deep(.category-markdown h2) {
    font-size: 18px !important;
    margin: 28px 0 16px 0 !important;
  }

  :deep(.category-markdown h3) {
    font-size: 16px !important;
    margin: 24px 0 14px 0 !important;
  }

  :deep(.category-markdown p) {
    font-size: 14px !important;
    line-height: 1.8 !important;
    margin-bottom: 16px !important;
  }

  :deep(.category-markdown li) {
    font-size: 14px !important;
    line-height: 1.8 !important;
  }

  :deep(.category-markdown table) {
    font-size: 13px !important;
    width: 100% !important;
    display: block !important;
    overflow-x: auto !important;
  }

  :deep(.category-markdown table th),
  :deep(.category-markdown table td) {
    padding: 10px 12px !important;
    min-width: 80px !important;
  }

  :deep(.category-markdown img) {
    max-width: 100% !important;
    height: auto !important;
  }

  .pagination-section {
    padding: 30px 0 !important;
    margin-top: 30px !important;
    width: 100% !important;
    overflow-x: hidden !important;
  }

  :deep(.pagination .el-pagination) {
    flex-wrap: wrap !important;
    gap: 8px !important;
  }

  :deep(.pagination .el-pager li) {
    min-width: 32px !important;
    height: 32px !important;
    line-height: 32px !important;
    font-size: 13px !important;
    margin: 0 3px !important;
  }

  :deep(.pagination .btn-prev),
  :deep(.pagination .btn-next) {
    min-width: 32px !important;
    height: 32px !important;
    font-size: 13px !important;
  }

  :deep(.pagination .el-input__inner) {
    font-size: 13px !important;
    height: 32px !important;
  }
}

@media (min-width: 1920px) {
  .page-header {
    padding: 108px 0 81px;
  }

  .page-header-inner {
    max-width: 1890px;
    padding: 0 54px;
  }

  .products-main-section {
    padding: 54px 0 108px;
  }

  .products-main-inner {
    max-width: 2295px;
    padding: 0 54px;
  }

  .content-layout {
    grid-template-columns: 500px 1fr;
    gap: 43px;
  }

  .category-sidebar {
    padding: 24px;
  }

  .sidebar-title {
    font-size: 27px;
    margin-bottom: 22px;
    padding-bottom: 16px;
  }

  .sidebar-title::after {
    width: 54px;
    height: 4px;
  }

  .latest-products-title {
    font-size: 24px;
    margin-bottom: 22px;
    padding-bottom: 16px;
  }

  .latest-products-title::after {
    width: 54px;
    height: 4px;
  }

  .latest-product-item {
    padding: 16px;
  }

  .latest-product-image {
    width: 81px;
    height: 81px;
  }

  .category-header {
    margin-bottom: 32px;
  }

  .category-title {
    font-size: 32px;
    margin-bottom: 16px;
  }

  .category-title::after {
    width: 81px;
    height: 4px;
    bottom: -5px;
  }

  .category-slogan {
    font-size: 32px;
    margin: 22px 0;
  }

  .category-subtitle {
    font-size: 19px;
    line-height: 1.6;
  }

  .feature-boxes {
    grid-template-columns: repeat(2, 0.6fr);
    gap: 32px;
    margin-top: 43px;
    padding-bottom: 43px;
  }

  .feature-box {
    padding: 43px 38px;
    min-height: 270px;
    border-radius: 14px;
    border-bottom: 4px solid #FF0000;
  }

  .feature-icon {
    width: 68px;
    height: 68px;
    border-radius: 11px;
    margin-bottom: 22px;
  }

  .feature-title {
    font-size: 24px;
    margin-bottom: 14px;
  }

  .feature-desc {
    font-size: 19px;
    line-height: 1.6;
  }
  .product-tag {
    font-size: 14px !important;
  }
  .product-footer .el-button {
        font-size: 16px !important;
        padding: 11px 24px;
        border-radius: 27px;
        height: 40px;
    }
  .products-grid {
    gap: 30px;
  }

  .product-col {
    width: calc(33.333% - 21px);
  }

  .pagination-section {
    padding: 54px 0;
    margin-top: 54px;
  }

  :deep(.pagination .el-pager li) {
    font-size: 18px;
    padding: 8px 14px;
    margin: 0 5px;
  }

  :deep(.pagination .btn-prev),
  :deep(.pagination .btn-next) {
    font-size: 18px;
    padding: 8px 14px;
  }
}

@media (min-width: 2560px) {
  .page-header {
    padding: 144px 0 108px;
  }

  .page-header-inner {
    max-width: 2520px;
    padding: 0 72px;
  }

  .products-main-section {
    padding: 72px 0 144px;
  }

  .products-main-inner {
    max-width: 3060px;
    padding: 0 72px;
  }

  .content-layout {
    grid-template-columns: 666px 1fr;
    gap: 58px;
  }

  .category-sidebar {
    padding: 32px;
  }

  .sidebar-title {
    font-size: 36px;
    margin-bottom: 29px;
    padding-bottom: 22px;
  }

  .sidebar-title::after {
    width: 72px;
    height: 5px;
  }

  .latest-products-title {
    font-size: 32px;
    margin-bottom: 29px;
    padding-bottom: 22px;
  }

  .latest-products-title::after {
    width: 72px;
    height: 5px;
  }

  .latest-product-item {
    padding: 21px;
  }

  .latest-product-image {
    width: 108px;
    height: 108px;
  }

  .category-header {
    margin-bottom: 43px;
  }

  .category-title {
    font-size: 43px;
    margin-bottom: 22px;
  }

  .category-title::after {
    width: 108px;
    height: 5px;
    bottom: -7px;
  }

  .category-slogan {
    font-size: 43px;
    margin: 29px 0;
  }

  .category-subtitle {
    font-size: 25px;
    line-height: 1.6;
  }

  .feature-boxes {
    grid-template-columns: repeat(2, 0.6fr);
    gap: 43px;
    margin-top: 58px;
    padding-bottom: 58px;
  }

  .feature-box {
    padding: 58px 50px;
    min-height: 360px;
    border-radius: 18px;
    border-bottom: 5px solid #FF0000;
  }

  .feature-icon {
    width: 90px;
    height: 90px;
    border-radius: 14px;
    margin-bottom: 29px;
  }

  .feature-title {
    font-size: 32px;
    margin-bottom: 18px;
  }

  .feature-desc {
    font-size: 25px;
    line-height: 1.6;
  }

  .products-grid {
    gap: 43px;
  }

  .product-col {
    width: calc(33.333% - 29px);
  }

  .pagination-section {
    padding: 72px 0;
    margin-top: 72px;
  }

  :deep(.pagination .el-pager li) {
    font-size: 24px;
    padding: 10px 18px;
    margin: 0 7px;
  }

  :deep(.pagination .btn-prev),
  :deep(.pagination .btn-next) {
    font-size: 24px;
    padding: 10px 18px;
  }
}

@media (max-width: 576px) {
  .products-main-section {
    padding: 30px 0 50px !important;
  }

  .products-main-inner {
    padding: 0 15px !important;
  }

  .content-layout {
    gap: 20px !important;
  }

  .category-sidebar {
    padding: 15px !important;
  }

  .products-content {
    width: 100% !important;
    min-width: 0;
    overflow-x: hidden;
  }

  .category-header {
    margin-bottom: 20px;
    text-align: center;
  }

  .category-title {
    font-size: 20px;
    margin-bottom: 8px;
  }

  .category-title::after {
    width: 40px;
  }

  .category-slogan {
    font-size: 16px;
    margin: 10px 0;
  }

  .category-subtitle {
    font-size: 13px;
    line-height: 1.5;
  }

  .feature-boxes {
    gap: 12px !important;
    margin-top: 20px !important;
  }

  .feature-box {
    padding: 16px !important;
    flex-direction: row !important;
    align-items: flex-start !important;
    text-align: left !important;
  }

  .feature-icon {
    width: 40px !important;
    height: 40px !important;
    margin-right: 12px !important;
    margin-bottom: 0 !important;
  }

  .feature-content {
    flex: 1 !important;
  }

  .feature-title {
    font-size: 15px !important;
    margin-bottom: 6px !important;
  }

  .feature-desc {
    font-size: 12px !important;
    line-height: 1.5 !important;
  }

  .products-grid {
    gap: 12px !important;
  }

  .product-col {
    width: 100% !important;
  }

  .product-card {
    min-height: 220px !important;
  }

  .product-image {
    height: 130px !important;
    margin: 12px auto 0 !important;
  }

  .product-tag {
    top: 6px !important;
    right: 6px !important;
    padding: 4px 10px !important;
    font-size: 10px !important;
  }

  .product-content {
    padding: 12px !important;
  }

  .product-name {
    font-size: 13px !important;
    margin-bottom: 6px !important;
  }

  .product-footer .el-button {
    font-size: 12px !important;
    padding: 6px 14px !important;
    width: 100% !important;
  }

  .pagination-section {
    padding: 25px 0 !important;
    margin-top: 25px !important;
    width: 100% !important;
    overflow-x: hidden !important;
  }

  :deep(.pagination .el-pagination) {
    flex-wrap: wrap !important;
    gap: 6px !important;
    justify-content: center !important;
  }

  :deep(.pagination .el-pager li) {
    min-width: 30px !important;
    height: 30px !important;
    line-height: 30px !important;
    font-size: 12px !important;
    margin: 0 2px !important;
  }

  :deep(.pagination .btn-prev),
  :deep(.pagination .btn-next) {
    min-width: 30px !important;
    height: 30px !important;
    font-size: 12px !important;
  }

  :deep(.pagination .el-input__inner) {
    font-size: 12px !important;
    height: 30px !important;
  }

  :deep(.pagination .el-input__suffix) {
    line-height: 30px !important;
  }

  .category-markdown {
    padding: 18px !important;
    margin-top: 24px !important;
    width: 350px !important;
    max-width: 100% !important;
    box-sizing: border-box !important;
    overflow-x: hidden !important;
  }

  :deep(.category-markdown h1) {
    font-size: 22px !important;
    margin-bottom: 20px !important;
  }

  :deep(.category-markdown h1::before),
  :deep(.category-markdown h1::after) {
    display: none !important;
  }

  :deep(.category-markdown h2) {
    font-size: 17px !important;
    margin: 24px 0 14px 0 !important;
  }

  :deep(.category-markdown h3) {
    font-size: 15px !important;
    margin: 20px 0 12px 0 !important;
    padding-left: 14px !important;
  }

  :deep(.category-markdown p) {
    font-size: 13px !important;
    line-height: 1.75 !important;
    margin-bottom: 14px !important;
  }

  :deep(.category-markdown li) {
    font-size: 13px !important;
    line-height: 1.75 !important;
    margin-bottom: 12px !important;
  }

  :deep(.category-markdown ul),
  :deep(.category-markdown ol) {
    padding-left: 28px !important;
    margin-bottom: 18px !important;
  }

  :deep(.category-markdown table) {
    font-size: 12px !important;
    display: block !important;
    overflow-x: auto !important;
    white-space: nowrap !important;
  }

  :deep(.category-markdown img) {
    max-width: 100% !important;
    height: auto !important;
  }
}

@media (max-width: 360px) {
  .products-main-section {
    padding: 24px 0 40px;
  }

  .products-main-inner {
    padding: 0 12px;
  }

  .products-content {
    width: 100% !important;
    min-width: 0;
    overflow-x: hidden;
  }

  .category-header {
    margin-bottom: 16px;
    text-align: center;
  }

  .category-title {
    font-size: 18px;
    margin-bottom: 6px;
  }

  .category-title::after {
    width: 36px;
  }

  .category-slogan {
    font-size: 14px;
    margin: 8px 0;
  }

  .category-subtitle {
    font-size: 12px;
    line-height: 1.5;
  }

  .feature-box {
    padding: 12px;
  }

  .feature-icon {
    width: 36px;
    height: 36px;
    margin-right: 10px;
  }

  .feature-title {
    font-size: 13px;
  }

  .feature-desc {
    font-size: 11px;
  }

  .products-grid {
    gap: 10px;
  }

  .product-col {
    width: 100%;
  }

  .product-image {
    height: 110px;
  }

  .product-name {
    font-size: 12px;
  }

  .pagination-section {
    padding: 20px 0 !important;
    margin-top: 20px !important;
    width: 100% !important;
    overflow-x: hidden !important;
  }

  :deep(.pagination .el-pagination) {
    flex-wrap: wrap !important;
    gap: 6px !important;
    justify-content: center !important;
  }

  :deep(.pagination .el-pager li) {
    min-width: 28px !important;
    height: 28px !important;
    line-height: 28px !important;
    font-size: 12px !important;
    margin: 0 2px !important;
  }

  :deep(.pagination .btn-prev),
  :deep(.pagination .btn-next) {
    min-width: 28px !important;
    height: 28px !important;
    font-size: 12px !important;
  }

  :deep(.pagination .el-input__inner) {
    font-size: 12px !important;
    height: 28px !important;
  }

  :deep(.pagination .el-input__suffix) {
    line-height: 28px !important;
  }

  .category-markdown {
    padding: 16px !important;
    margin-top: 20px !important;
    width: 100% !important;
    max-width: 100% !important;
    box-sizing: border-box !important;
    overflow-x: hidden !important;
  }

  :deep(.category-markdown h1) {
    font-size: 20px !important;
    margin-bottom: 16px !important;
    padding: 0 10px !important;
  }

  :deep(.category-markdown h1::before),
  :deep(.category-markdown h1::after) {
    display: none !important;
  }

  :deep(.category-markdown h2) {
    font-size: 16px !important;
    margin: 20px 0 12px 0 !important;
    padding-bottom: 8px !important;
  }

  :deep(.category-markdown h3) {
    font-size: 15px !important;
    margin: 16px 0 10px 0 !important;
    padding-left: 12px !important;
  }

  :deep(.category-markdown p) {
    font-size: 13px !important;
    line-height: 1.7 !important;
    margin-bottom: 12px !important;
  }

  :deep(.category-markdown li) {
    font-size: 13px !important;
    line-height: 1.7 !important;
    margin-bottom: 10px !important;
  }

  :deep(.category-markdown ul),
  :deep(.category-markdown ol) {
    padding-left: 24px !important;
    margin-bottom: 16px !important;
  }

  :deep(.category-markdown table) {
    font-size: 12px !important;
    display: block !important;
    overflow-x: auto !important;
    white-space: nowrap !important;
  }

  :deep(.category-markdown table th),
  :deep(.category-markdown table td) {
    padding: 8px 10px !important;
    min-width: 100px !important;
  }

  :deep(.category-markdown img) {
    max-width: 100% !important;
    height: auto !important;
  }
}

.product-card {
  background: #ffffff;
  border: 1px solid #e2e8f0;
  /* border-radius: 16px; */
  overflow: hidden;
  transition: all 0.3s ease;
  height: 100%;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.06);
}

.product-card:hover {
  transform: translateY(-8px);
  box-shadow: 0 12px 40px rgba(255, 0, 0, 0.15);
  border-color: #FF0000;
}

.product-image {
  position: relative;
  width: 95%;
  height: 200px;
  margin: 16px auto 0;
  padding: 3px;
  background: linear-gradient(135deg, #f5f5f5 0%, #e8e8e8 100%);
  overflow: hidden;
}

.product-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  /* border-radius: 12px; */
}

.product-tag {
  position: absolute;
  top: 12px;
  right: 12px;
  padding: 6px 16px;
  background: #FF0000;
  color: #ffffff;
  font-size: 12px;
  font-weight: 600;
  border-radius: 20px;
  box-shadow: 0 2px 8px rgba(255, 0, 0, 0.2);
}

.product-content {
  padding: 16px;
  flex-grow: 1;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  overflow: hidden;
}

.product-name {
  font-size: 18px;
  font-weight: 600;
  color: #1a2a4a;
  margin: 0 0 12px 0;
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
  font-size: 14px;
  color: #4a5568;
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
  margin-top: 12px;
}

.product-footer .el-button {
  font-size: 14px;
  padding: 8px 20px;
  border-radius: 20px;
  background: #FF0000;
  border: none;
  font-weight: 500;
  transition: all 0.3s ease;
}

.product-footer .el-button:hover {
  background: #B22222;
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(255, 0, 0, 0.35);
}

.no-products {
  text-align: center;
  padding: 80px 40px;
  background: #ffffff;
  border-radius: 16px;
  border: 1px solid #e2e8f0;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.06);
}

.no-products-icon {
  font-size: 64px;
  color: #cbd5e1;
  margin-bottom: 24px;
}

.no-products-title {
  font-size: 24px;
  font-weight: 600;
  color: #1a2a4a;
  margin: 0 0 12px 0;
}

.no-products-desc {
  font-size: 16px;
  color: #4a5568;
  margin: 0 0 24px 0;
  line-height: 1.6;
}

.no-products .el-button {
  padding: 12px 32px;
  border-radius: 12px;
  font-size: 16px;
  font-weight: 600;
}
:deep(.search-input .el-input__prefix-inner) {
  margin-left: 20px;
}

.category-markdown {
  margin-top: 40px;
  padding: 32px 40px;
  background: #ffffff;
  border-radius: 16px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.06);
}

:deep(.category-markdown h1) {
  font-size: 28px;
  font-weight: 700;
  color: #FF0000;
  margin: 0 0 32px 0;
  padding: 0 20px;
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
}

:deep(.category-markdown h1::before),
:deep(.category-markdown h1::after) {
  content: '';
  flex: 1;
  height: 2px;
  background: linear-gradient(90deg, transparent, #FF0000, transparent);
}

:deep(.category-markdown h1::before) {
  margin-right: 20px;
}

:deep(.category-markdown h1::after) {
  margin-left: 20px;
}

:deep(.category-markdown h2) {
  font-size: 22px;
  font-weight: 600;
  color: #1a2a4a;
  margin: 32px 0 20px 0;
  padding-bottom: 12px;
  border-bottom: 2px solid #FF0000;
  display: inline-block;
}

:deep(.category-markdown h2::before) {
  display: none;
}

:deep(.category-markdown h2::after) {
  display: none;
}

:deep(.category-markdown h3) {
  font-size: 18px;
  font-weight: 600;
  color: #1a2a4a;
  margin: 28px 0 16px 0;
  padding-left: 16px;
  border-left: 4px solid #FF0000;
}

:deep(.category-markdown h3::before) {
  display: none;
}

:deep(.category-markdown h3::after) {
  display: none;
}

:deep(.category-markdown p) {
  font-size: 15px;
  color: #4a5568;
  line-height: 1.9;
  margin: 0 0 18px 0;
  text-align: justify;
}

:deep(.category-markdown ul),
:deep(.category-markdown ol) {
  margin: 0 0 20px 0;
  padding-left: 40px;
  list-style: none;
}

:deep(.category-markdown li) {
  font-size: 15px;
  color: #4a5568;
  line-height: 1.9;
  margin-bottom: 16px;
  position: relative;
  padding-left: 0;
}

:deep(.category-markdown ul li) {
  padding-left: 28px;
}

:deep(.category-markdown ul li::before) {
  content: '';
  position: absolute;
  left: 0;
  top: 12px;
  width: 12px;
  height: 10px;
  background: #FF0000;
  border-radius: 50%;
}

:deep(.category-markdown ol) {
  counter-reset: item;
  padding: 0 !important;
  margin: 0 0 20px 0 !important;
  list-style: none !important;
  list-style-type: none !important;
  font-size: 0 !important;
}

:deep(.category-markdown ol > li) {
  counter-increment: item !important;
  list-style: none !important;
  list-style-type: none !important;
  position: relative !important;
  padding: 0 0 0 50px !important;
  margin: 0 0 16px 0 !important;
  display: block !important;
}

:deep(.category-markdown ol > li::before) {
  content: counter(item) !important;
  position: absolute !important;
  left: 0 !important;
  top: 0 !important;
  width: 32px !important;
  height: 32px !important;
  background: #FF0000 !important;
  color: #fff !important;
  font-size: 14px !important;
  font-weight: 600 !important;
  line-height: 32px !important;
  text-align: center !important;
  border-radius: 50% !important;
}

:deep(.category-markdown ol > li p) {
  display: inline !important;
  margin: 0 !important;
  font-size: 15px !important;
  color: #4a5568 !important;
  line-height: 1.9 !important;
}

:deep(.category-markdown table) {
  width: 100%;
  border-collapse: collapse;
  margin: 24px 0;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
}

:deep(.category-markdown table tr) {
  border-bottom: 1px solid #e2e8f0;
  transition: background-color 0.2s ease;
}

:deep(.category-markdown table th),
:deep(.category-markdown table td) {
  padding: 16px 20px;
  text-align: left;
  border-bottom: 1px solid #e2e8f0;
}

:deep(.category-markdown table th) {
  background: #fff5f5;
  font-weight: 600;
  color: #1a2a4a;
  font-size: 14px;
}

:deep(.category-markdown table tbody tr:nth-child(even)) {
  background: #f8fafc;
}

:deep(.category-markdown table tbody tr:hover),
:deep(.category-markdown table tbody tr:nth-child(even):hover) {
  background: #fff5f5;
}

:deep(.category-markdown table td) {
  font-size: 14px;
  color: #4a5568;
}

:deep(.category-markdown img) {
  max-width: 100%;
  height: auto;
  border-radius: 8px;
  margin: 20px 0;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

:deep(.category-markdown img:hover) {
  transform: scale(1.02);
  box-shadow: 0 6px 20px rgba(0, 0, 0, 0.15);
}

:deep(.category-markdown strong) {
  color: #FF0000;
  font-weight: 600;
}

@media (min-width: 1900px) {
  .category-markdown {
    padding: 43px;
    margin-top: 54px;
    border-radius: 16px;
  }

  :deep(.category-markdown h1) {
    font-size: 38px;
    margin: 0 0 32px 0;
    padding: 27px 32px;
  }

  :deep(.category-markdown h1::before) {
    width: 8px;
    height: 38px;
    margin-right: 16px;
  }

  :deep(.category-markdown h1::after) {
    height: 4px;
    margin-top: 20px;
  }

  :deep(.category-markdown h2) {
    font-size: 30px;
    margin: 38px 0 22px 0;
    padding-bottom: 16px;
  }

  :deep(.category-markdown h2::before) {
    width: 5px;
    height: 27px;
    margin-right: 14px;
  }

  :deep(.category-markdown h3) {
    font-size: 25px;
    margin: 32px 0 18px 0;
    padding-left: 22px;
  }

  :deep(.category-markdown h3::before) {
    width: 4px;
    height: 22px;
    margin-right: 11px;
  }

  :deep(.category-markdown p) {
    font-size: 20px;
    line-height: 1.9;
    margin-bottom: 16px;
  }

  :deep(.category-markdown li) {
    font-size: 20px;
    line-height: 1.8;
    margin-bottom: 14px;
  }

  :deep(.category-markdown ul),
  :deep(.category-markdown ol) {
    padding-left: 43px;
    margin-bottom: 22px;
  }

  :deep(.category-markdown ul li::before) {
    width: 8px;
    height: 8px;
    margin-right: 14px;
  }

  :deep(.category-markdown ol > li) {
    padding: 0 0 0 68px;
    margin: 0 0 22px 0;
  }

  :deep(.category-markdown ol > li::before) {
    width: 43px;
    height: 43px;
    font-size: 19px;
    line-height: 43px;
  }

  :deep(.category-markdown ol > li p) {
    font-size: 20px;
    line-height: 1.9;
  }

  :deep(.category-markdown table) {
    margin: 32px 0;
    border-radius: 14px;
  }

  :deep(.category-markdown table tr) {
    border-bottom: 2px solid #e2e8f0;
  }

  :deep(.category-markdown table th) {
    font-size: 20px;
    padding: 20px 27px;
  }

  :deep(.category-markdown table td) {
    font-size: 20px;
    padding: 20px 27px;
  }

  :deep(.category-markdown img) {
    margin: 32px 0;
    border-radius: 14px;
    box-shadow: 0 6px 18px rgba(0, 0, 0, 0.1);
  }

  :deep(.category-markdown img:hover) {
    box-shadow: 0 9px 27px rgba(0, 0, 0, 0.15);
  }

  :deep(.category-markdown strong) {
    font-size: 20px;
  }
}

@media (min-width: 2561px) {
  .category-markdown {
    padding: 58px;
    margin-top: 72px;
    border-radius: 22px;
  }

  :deep(.category-markdown h1) {
    font-size: 50px;
    margin: 0 0 43px 0;
    padding: 36px 43px;
  }

  :deep(.category-markdown h1::before) {
    width: 11px;
    height: 50px;
    margin-right: 22px;
  }

  :deep(.category-markdown h1::after) {
    height: 5px;
    margin-top: 27px;
  }

  :deep(.category-markdown h2) {
    font-size: 40px;
    margin: 50px 0 29px 0;
    padding-bottom: 22px;
  }

  :deep(.category-markdown h2::before) {
    width: 6px;
    height: 36px;
    margin-right: 18px;
  }

  :deep(.category-markdown h3) {
    font-size: 33px;
    margin: 43px 0 24px 0;
    padding-left: 29px;
  }

  :deep(.category-markdown h3::before) {
    width: 5px;
    height: 29px;
    margin-right: 14px;
  }

  :deep(.category-markdown p) {
    font-size: 27px;
    line-height: 1.9;
    margin-bottom: 22px;
  }

  :deep(.category-markdown li) {
    font-size: 27px;
    line-height: 1.8;
    margin-bottom: 18px;
  }

  :deep(.category-markdown ul),
  :deep(.category-markdown ol) {
    padding-left: 58px;
    margin-bottom: 29px;
  }

  :deep(.category-markdown ul li::before) {
    width: 11px;
    height: 11px;
    margin-right: 18px;
  }

  :deep(.category-markdown ol > li) {
    padding: 0 0 0 90px;
    margin: 0 0 29px 0;
  }

  :deep(.category-markdown ol > li::before) {
    width: 57px;
    height: 57px;
    font-size: 25px;
    line-height: 57px;
  }

  :deep(.category-markdown ol > li p) {
    font-size: 27px;
    line-height: 1.9;
  }

  :deep(.category-markdown table) {
    margin: 43px 0;
    border-radius: 18px;
  }

  :deep(.category-markdown table tr) {
    border-bottom: 3px solid #e2e8f0;
  }

  :deep(.category-markdown table th) {
    font-size: 27px;
    padding: 27px 36px;
  }

  :deep(.category-markdown table td) {
    font-size: 27px;
    padding: 27px 36px;
  }

  :deep(.category-markdown img) {
    margin: 43px 0;
    border-radius: 18px;
    box-shadow: 0 8px 24px rgba(0, 0, 0, 0.1);
  }

  :deep(.category-markdown img:hover) {
    box-shadow: 0 12px 36px rgba(0, 0, 0, 0.15);
  }

  :deep(.category-markdown strong) {
    font-size: 27px;
  }
}
</style>
