<template>
  <div class="sidebar">
    <div class="category-sidebar">
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
        :current-node-key="currentCategory"
        :expanded-keys="expandedKeys"
        node-key="id"
        :highlight-current="true"
        :expand-on-click-node="false"
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
import { ref, onMounted, watch } from 'vue'
import { getProductById, getNewProducts, getCategoryTreeFromApi, type Product, type CategoryTreeNode } from '@/data/products'

interface TreeNode {
  id: string
  label: string
  children?: TreeNode[]
}

const categoryTree = ref<CategoryTreeNode[]>([])
const searchQuery = ref('')
const currentCategory = ref('all')
const expandedKeys = ref<string[]>([])
const newProducts = ref<Product[]>([])

async function loadCategoryTree() {
  categoryTree.value = await getCategoryTreeFromApi()
}

async function loadNewProducts() {
  newProducts.value = await getNewProducts(5)
}

const router = useRouter()
const route = useRoute()

function findParentNode(nodes: CategoryTreeNode[], targetId: string): string | null {
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

function isParentNode(nodeId: string): boolean {
  const node = categoryTree.value.find(n => n.id === nodeId)
  if (!node) return false
  return Boolean(node.children && node.children.length > 0)
}

function updateExpandedKeysForCategory(categoryId: string) {
  if (!categoryId) {
    currentCategory.value = 'all'
    expandedKeys.value = []
    return
  }

  currentCategory.value = categoryId

  if (!isParentNode(categoryId)) {
    const parentKey = findParentNode(categoryTree.value, categoryId)
    if (parentKey) {
      expandedKeys.value = [parentKey]
    } else {
      expandedKeys.value = []
    }
  } else {
    expandedKeys.value = [categoryId]
  }
}

onMounted(async () => {
  await Promise.all([loadCategoryTree(), loadNewProducts()])

  const categoryParam = route.query.category as string
  updateExpandedKeysForCategory(categoryParam)
})

watch(() => route.query.category, (newCategory) => {
  updateExpandedKeysForCategory(newCategory as string)
})

watch(categoryTree, (newTree) => {
  if (newTree.length > 0) {
    const categoryParam = route.query.category as string
    if (categoryParam) {
      updateExpandedKeysForCategory(categoryParam)
    }
  }
}, { immediate: false })

const defaultProps = {
  children: 'children',
  label: 'label'
}

function handleSearch(value: string) {
  searchQuery.value = value
}

function handleNodeClick(data: TreeNode) {
  currentCategory.value = data.id
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
    }
  }
}
</script>

<style scoped>
/* .sidebar {
  flex-shrink: 0;
  width: 370px;
} */

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
</style>
