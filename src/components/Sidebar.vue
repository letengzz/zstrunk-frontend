<template>
  <div class="sidebar">
    <div class="sidebar-toggle" @click="toggleSidebar">
      <div class="i-ep-menu" w24 h24></div>
    </div>

    <div class="sidebar-overlay" :class="{ show: isSidebarOpen }" @click="closeSidebar"></div>

    <div class="sidebar-drawer" :class="{ open: isSidebarOpen }">
      <div class="sidebar-header">
        <h3 class="sidebar-title">Categories</h3>
        <div class="sidebar-close" @click="closeSidebar">
          <div class="i-ep-close" w20 h20></div>
        </div>
      </div>

      <div class="sidebar-content">
        <div class="sidebar-search">
          <el-input
            :model-value="searchQuery"
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
          :current-node-key="computedCurrentCategory"
          :expanded-keys="treeExpandedKeys"
          node-key="id"
          :highlight-current="true"
          :expand-on-click-node="true"
          :accordion="true"
          @node-click="handleNodeClick"
          class="category-tree"
        />

        <div class="latest-products-sidebar">
          <h4 class="latest-products-title">Latest Products</h4>
          <div class="latest-products-list" v-if="newProducts.length > 0">
            <div
              class="latest-product-item"
              v-for="product in newProducts"
              :key="product.id"
              @click="goToProduct(product.id)"
            >
              <img :src="product.image" :alt="product.name" class="latest-product-image" />
              <div class="latest-product-info">
                <p class="latest-product-name">{{ product.name }}</p>
              </div>
            </div>
          </div>
          <div v-else class="no-products-message">
            <p>No new products available</p>
          </div>
        </div>
      </div>
    </div>

    <div class="category-sidebar desktop-only">
      <h3 class="sidebar-title">Categories</h3>
      <div class="sidebar-search">
        <el-input
          :model-value="searchQuery"
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
        :current-node-key="computedCurrentCategory"
        :expanded-keys="treeExpandedKeys"
        node-key="id"
        :highlight-current="true"
        :expand-on-click-node="true"
        :accordion="true"
        @node-click="handleNodeClick"
        class="category-tree"
      />
      <div class="latest-products-sidebar">
        <h4 class="latest-products-title">Latest Products</h4>
        <div class="latest-products-list" v-if="newProducts.length > 0">
          <div
            class="latest-product-item"
            v-for="product in newProducts"
            :key="product.id"
            @click="goToProduct(product.id)"
          >
            <img :src="product.image" :alt="product.name" class="latest-product-image" />
            <div class="latest-product-info">
              <p class="latest-product-name">{{ product.name }}</p>
            </div>
          </div>
        </div>
        <div v-else class="no-products-message">
          <p>No new products available</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useRouter, useRoute } from 'vue-router'
import { ref, onMounted, watch, computed } from 'vue'
import { getProductById, getNewProducts, getCategoryTreeFromApi, type Product, type CategoryTreeNode } from '@/data/products'

interface TreeNode {
  id: string
  label: string
  children?: TreeNode[]
}

interface Props {
  searchQuery: string
  currentCategory: string
  expandedKeys: string[]
}

const props = defineProps<Props>()
const emit = defineEmits<{
  (e: 'update:searchQuery', value: string): void
  (e: 'category-change', value: string): void
}>()

const categoryTree = ref<CategoryTreeNode[]>([])
const newProducts = ref<Product[]>([])
const isSidebarOpen = ref(false)
const treeExpandedKeys = ref<string[]>([])

const computedCurrentCategory = computed({
  get: () => props.currentCategory,
  set: (value: string) => emit('category-change', value)
})

function toggleSidebar() {
  isSidebarOpen.value = !isSidebarOpen.value
}

function closeSidebar() {
  isSidebarOpen.value = false
}

async function loadCategoryTree() {
  categoryTree.value = await getCategoryTreeFromApi()
}

async function loadNewProducts() {
  newProducts.value = await getNewProducts(5)
}

const router = useRouter()
const route = useRoute()

onMounted(async () => {
  await Promise.all([loadCategoryTree(), loadNewProducts()])

  if (!props.currentCategory || props.currentCategory === 'all') {
    const categoryParam = route.query.category as string
    if (categoryParam) {
      emit('category-change', categoryParam)
    }
  }
})

watch(() => route.query.category, (newCategory) => {
  if (newCategory) {
    emit('category-change', newCategory as string)
  }
})

const defaultProps = {
  children: 'children',
  label: 'label'
}

function handleSearch(value: string) {
  emit('update:searchQuery', value)
}

function handleNodeClick(data: TreeNode) {
  emit('category-change', data.id)

  if (data.id === 'all') {
    router.push({ path: '/products' })
  } else {
    router.push({ path: '/products', query: { category: data.id } })
  }
}

async function goToProduct(id: number) {
  const product = await getProductById(id)
  if (product) {
    if (product.category === 'tanker') {
      router.push(`/truck/${id}`)
    } else if (product.category === 'excavator') {
      router.push(`/excavator/${id}`)
    } else {
      router.push(`/truck/${id}`)
    }
  }
}
</script>

<style scoped>
.sidebar {
  position: relative;
}

.sidebar-toggle {
  display: none;
  position: fixed;
  top: 100px;
  left: 10px;
  z-index: 1002;
  width: 44px;
  height: 44px;
  background: #FF0000;
  border-radius: 50%;
  cursor: pointer;
  align-items: center;
  justify-content: center;
  box-shadow: 0 4px 12px rgba(255, 0, 0, 0.3);
}

.sidebar-overlay {
  display: none;
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
  z-index: 1000;
  opacity: 0;
  transition: opacity 0.3s ease;
  pointer-events: none;
}

.sidebar-overlay.show {
  opacity: 1;
  pointer-events: auto;
}

.sidebar-drawer {
  display: none;
  position: fixed;
  top: 0;
  left: -100%;
  width: 85%;
  max-width: 320px;
  height: 100vh;
  background: #ffffff;
  z-index: 1001;
  transition: left 0.3s ease;
  overflow-y: auto;
  box-shadow: 4px 0 20px rgba(0, 0, 0, 0.15);
}

.sidebar-drawer.open {
  left: 0;
}

.sidebar-header {
  display: none;
  position: sticky;
  top: 0;
  background: #ffffff;
  padding: 16px 20px;
  border-bottom: 1px solid #e2e8f0;
  z-index: 10;
}

.sidebar-close {
  display: none;
  position: absolute;
  right: 16px;
  top: 50%;
  transform: translateY(-50%);
  width: 36px;
  height: 36px;
  background: #f5f5f5;
  border-radius: 50%;
  cursor: pointer;
  align-items: center;
  justify-content: center;
}

.sidebar-close:hover {
  background: #e54343;
  color: #ffffff;
}

.sidebar-content {
  padding: 16px;
}

.desktop-only {
  display: block;
}

@media (max-width: 768px) {
  .sidebar-toggle {
    display: flex;
  }

  .sidebar-overlay {
    display: flex;
  }

  .sidebar-drawer {
    display: block;
  }

  .sidebar-header {
    display: flex;
    align-items: center;
  }

  .sidebar-close {
    display: flex;
  }

  .desktop-only {
    display: none;
  }

  .category-sidebar {
    padding: 15px;
  }

  .sidebar-title {
    font-size: 18px;
  }

  :deep(.category-tree .el-tree-node__label) {
    font-size: 14px;
  }
}

@media (max-width: 576px) {
  .sidebar-drawer {
    width: 100%;
    max-width: 100%;
  }

  .sidebar-content {
    padding: 12px;
  }

  .sidebar-title {
    font-size: 17px;
  }

  .sidebar-search {
    margin-bottom: 12px;
  }

  :deep(.category-tree .el-tree-node__content) {
    padding: 8px 0;
  }

  :deep(.category-tree .el-tree-node__label) {
    font-size: 13px;
    padding: 4px 8px;
  }
}

@media (max-width: 480px) {
  .sidebar-toggle {
    width: 40px;
    height: 40px;
    top: 80px;
    left: 8px;
  }

  .sidebar-title {
    font-size: 16px;
  }

  .sidebar-content {
    padding: 10px;
  }

  :deep(.category-tree) {
    padding: 12px;
  }

  :deep(.category-tree .el-tree-node__label) {
    font-size: 13px;
  }

  .latest-products-sidebar {
    margin-top: 16px;
    padding-top: 16px;
  }

  .latest-products-title {
    font-size: 15px;
  }

  .latest-product-item {
    padding: 10px;
  }

  .latest-product-image {
    width: 50px;
    height: 50px;
  }

  .latest-product-name {
    font-size: 12px;
  }
}

.category-sidebar {
  background: #ffffff;
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
  padding: 16px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.06);
  border: 1px solid #e2e8f0;
}

:deep(.category-tree .el-tree-node__content) {
  padding: 8px 12px;
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
  font-size: 14px;
  color: #333;
  margin: 0;
  line-height: 1.4;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}

.no-products-message {
  padding: 20px;
  text-align: center;
  color: #999;
  font-size: 14px;
}

@media (min-width: 1920px) {
  .category-sidebar {
    padding: 24px !important;
  }

  .sidebar-search {
    margin-bottom: 22px !important;
  }

  :deep(.search-input-sidebar .el-input__wrapper) {
    box-shadow: 0 1px 8px rgba(0, 0, 0, 0.04) !important;
    border: 1px solid #e2e8f0 !important;
    background: #f8fafc !important;
  }

  :deep(.search-input-sidebar .el-input__inner) {
    font-size: 18px !important;
    padding: 11px 16px !important;
    height: 43px !important;
  }

  .sidebar-title {
    font-size: 27px !important;
    margin-bottom: 22px;
    padding-bottom: 16px;
  }

  .sidebar-title::after {
    width: 54px !important;
    height: 4px !important;
  }

  .category-tree {
    padding: 22px !important;
    box-shadow: 0 3px 16px rgba(0, 0, 0, 0.06);
    border: 1px solid #e2e8f0;
  }

  :deep(.category-tree .el-tree-node__content) {
    padding: 11px 16px !important;
  }

  :deep(.category-tree .el-tree-node__label) {
    font-size: 20px !important;
    line-height: 1.4 !important;
    padding: 5px 11px !important;
  }

  .latest-products-sidebar {
    margin-top: 32px;
    padding-top: 32px;
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

  .latest-products-list {
    gap: 16px;
  }

  .latest-product-item {
    padding: 16px;
    border-radius: 11px;
  }

  .latest-product-image {
    width: 81px;
    height: 81px;
    border-radius: 8px;
  }

  .latest-product-info {
    margin-left: 16px;
  }

  .latest-product-name {
    font-size: 19px;
    line-height: 1.4;
  }

  .no-products-message {
    padding: 27px;
    font-size: 19px;
  }
}

@media (min-width: 2560px) {
  .category-sidebar {
    padding: 32px;
  }

  .sidebar-search {
    margin-bottom: 29px;
  }

  :deep(.search-input-sidebar .el-input__wrapper) {
    box-shadow: 0 1px 11px rgba(0, 0, 0, 0.04) !important;
    border: 1px solid #e2e8f0 !important;
    background: #f8fafc !important;
  }

  :deep(.search-input-sidebar .el-input__inner) {
    font-size: 24px !important;
    padding: 14px 21px !important;
    height: 57px !important;
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

  .category-tree {
    padding: 29px;
    box-shadow: 0 4px 22px rgba(0, 0, 0, 0.06);
    border: 1px solid #e2e8f0;
  }

  :deep(.category-tree .el-tree-node__content) {
    padding: 14px 21px !important;
  }

  :deep(.category-tree .el-tree-node__label) {
    font-size: 27px !important;
    line-height: 1.4 !important;
    padding: 7px 14px !important;
  }

  .latest-products-sidebar {
    margin-top: 43px;
    padding-top: 43px;
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

  .latest-products-list {
    gap: 22px;
  }

  .latest-product-item {
    padding: 21px;
    border-radius: 14px;
  }

  .latest-product-image {
    width: 108px;
    height: 108px;
    border-radius: 11px;
  }

  .latest-product-info {
    margin-left: 21px;
  }

  .latest-product-name {
    font-size: 25px;
    line-height: 1.4;
  }

  .no-products-message {
    padding: 36px;
    font-size: 25px;
  }
}
</style>
