<template>
  <div class="products-page-container">
    <TopBar class="floating-topbar" />

    <div class="page-header">
      <div class="page-header-inner">
        <h1 class="page-title">Products</h1>
        <p class="page-description">Explore our wide range of high-quality products</p>
      </div>
    </div>

    <div class="products-main-section">
      <div class="products-main-inner">
        <div class="content-layout">
          <div class="sidebar">
            <div class="category-sidebar">
              <h3 class="sidebar-title">Categories</h3>
              <div class="sidebar-search">
                <el-input
                  v-model="searchQuery"
                  placeholder="Search products..."
                  class="search-input-sidebar"
                  @input="handleSearch"
                >
                  <template #prefix>
                    <div class="i-ep-search" w14 h14></div>
                  </template>
                </el-input>
              </div>
              <el-tree
                :data="categoryTree"
                :props="defaultProps"
                :current-node-key="currentCategory"
                node-key="id"
                :highlight-current="true"
                :expand-on-click-node="false"
                :accordion="true"
                @node-click="handleNodeClick"
                class="category-tree"
              />
            </div>
          </div>

          <div class="products-content">
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
                layout="total, sizes, prev, pager, next, jumper"
                :total="filteredProducts.length"
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                class="pagination"
              />
            </div>
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
import { useRouter, useRoute } from 'vue-router'
import { ref, computed, onMounted, watch } from 'vue'
import { products, getProductById } from '@/data/products'

const router = useRouter()
const route = useRoute()

const searchQuery = ref('')
const currentCategory = ref<string>('all')
const currentPage = ref(1)
const pageSize = ref(6)
const expandedKeys = ref<string[]>([])

onMounted(() => {
  const categoryParam = route.query.category as string
  if (categoryParam) {
    currentCategory.value = categoryParam
    updateExpandedKeys(categoryParam)
  }
})

watch(() => route.query.category, (newCategory) => {
  if (newCategory) {
    currentCategory.value = newCategory as string
    updateExpandedKeys(newCategory as string)
    currentPage.value = 1
  } else {
    currentCategory.value = 'all'
    updateExpandedKeys('all')
  }
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

const defaultProps = {
  children: 'children',
  label: 'label'
}

const mainCategoryMap: Record<string, 'tanker' | 'excavator'> = {
  'LiquidandPowerTransportTrailers': 'tanker',
  'ContainerSemiTrailer': 'tanker',
  'SemiTrailer': 'tanker',
  'ShacmanTrucks': 'tanker',
  'Accessories': 'tanker',
  'ExistingTrucksAndTrailers': 'tanker',
  'excavator': 'excavator'
}

const isMainCategory = (categoryId: string): boolean => {
  return categoryId in mainCategoryMap
}

const filteredProducts = computed(() => {
  let result = [...products]

  if (currentCategory.value !== 'all') {
    if (isMainCategory(currentCategory.value)) {
      const categoryType = mainCategoryMap[currentCategory.value]
      if (categoryType === 'tanker') {
        result = result.filter(product => product.category === 'tanker')
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

function handleNodeClick(data: TreeNode) {
  currentCategory.value = data.id
  currentPage.value = 1
  updateExpandedKeys(data.id)
  if (data.id === 'all') {
    router.push({ path: '/products' })
  } else {
    router.push({ path: '/products', query: { category: data.id } })
  }
}

function handleSearch() {
  currentPage.value = 1
}

function handleSizeChange(size: number) {
  pageSize.value = size
  currentPage.value = 1
}

function handleCurrentChange(page: number) {
  currentPage.value = page
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
  max-width: 1600px;
  margin: 0 auto;
  padding: 0 40px;
}

.content-layout {
  display: grid;
  grid-template-columns: 280px 1fr;
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
  border-radius: 16px;
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
  border-radius: 8px;
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
  border-radius: 12px;
  padding: 16px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.06);
  border: 1px solid #e2e8f0;
}

:deep(.category-tree .el-tree-node__content) {
  padding: 8px 12px;
  border-radius: 8px;
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
}

.products-content {
  flex: 1;
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
  border-radius: 8px;
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
  border: 1px solid #e2e8f0;
  border-radius: 16px;
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
  border-radius: 12px;
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
  /* -webkit-line-clamp: 2; */
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
</style>
