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
        node-key="id"
        :highlight-current="true"
        :expand-on-click-node="false"
        :accordion="true"
        @node-click="handleNodeClick"
        class="category-tree"
      />
      <div class="latest-products-sidebar">
        <h4 class="latest-products-title">Latest Products</h4>
        <div class="latest-products-list">
          <div
            class="latest-product-item"
            v-for="product in latestProducts"
            :key="product.id"
            @click="goToProduct(product.id)"
          >
            <img :src="product.image" :alt="product.name" class="latest-product-image" />
            <div class="latest-product-info">
              <p class="latest-product-name">{{ product.name }}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router'
import { getProductById } from '@/data/products'

interface TreeNode {
  id: string
  label: string
  children?: TreeNode[]
}

interface Product {
  id: number
  name: string
  image: string
  category: string
  desc?: string
  subCategory?: string
  tag?: string
}

defineProps<{
  searchQuery: string
  categoryTree: TreeNode[]
  currentCategory: string
  latestProducts: Product[]
}>()

const emit = defineEmits<{
  (e: 'update:searchQuery', value: string): void
  (e: 'category-change', value: string): void
}>()

const router = useRouter()

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
</style>
