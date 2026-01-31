<template>
  <div class="products-container dark-theme">
    <TopBar class="floating-topbar" />

    <!-- 页面标题部分 -->
    <div class="page-header">
      <div class="page-header-inner">
        <h1 class="page-title">Our Products</h1>
        <p class="page-description">We offer a wide range of high-quality tanker trucks and other heavy equipment to meet your transportation needs.</p>
      </div>
    </div>

    <!-- 分类筛选部分 -->
    <div class="category-filter-section">
      <div class="category-filter-inner">
        <!-- 搜索和排序 -->
        <div class="search-sort-section">
          <div class="search-box">
            <el-input
              v-model="searchQuery"
              placeholder="Search products..."
              prefix-icon="i-ep-search"
              class="search-input"
              @input="handleSearch"
            />
          </div>
          <div class="sort-box">
            <el-select
              v-model="sortBy"
              placeholder="Sort by"
              class="sort-select"
              @change="handleSort"
            >
              <el-option label="Default" value="default" />
              <el-option label="Newest" value="newest" />
            </el-select>
          </div>
        </div>

        <!-- 分类筛选 -->
        <div class="filter-tabs">
          <div
            class="filter-tab"
            :class="{ active: currentCategory === 'all' }"
            @click="setCategory('all')"
          >
            <span class="tab-name">All Products</span>
          </div>
          <div
            class="filter-tab"
            :class="{ active: currentCategory === 'tanker' }"
            @click="setCategory('tanker')"
          >
            <span class="tab-name">Tanker Trucks</span>
          </div>
          <div
            class="filter-tab"
            :class="{ active: currentCategory === 'excavator' }"
            @click="setCategory('excavator')"
          >
            <span class="tab-name">Excavators</span>
          </div>
          <div
            class="filter-tab"
            :class="{ active: currentCategory === 'trailer' }"
            @click="setCategory('trailer')"
          >
            <span class="tab-name">Trailers</span>
          </div>
        </div>
      </div>
    </div>

    <!-- 商品列表部分 -->
    <div class="products-section">
      <div class="products-inner">
        <!-- 商品网格 -->
        <div class="products-grid" v-if="filteredProducts.length > 0">
          <div class="product-col" v-for="product in paginatedProducts" :key="product.id">
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
                    <el-button type="primary" round @click="goToProduct(product.id, product.category)">View More</el-button>
                  </div>
                </div>
              </div>
            </el-card>
          </div>
        </div>

        <!-- 无商品提示 -->
        <div class="no-products" v-else>
          <div class="no-products-icon">
            <i class="i-ep-sell"></i>
          </div>
          <h3 class="no-products-title">No Products Found</h3>
          <p class="no-products-desc">Sorry, we couldn't find any products matching your criteria.</p>
          <el-button type="primary" @click="resetFilters">Reset Filters</el-button>
        </div>

        <!-- 分页 -->
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

    <!-- 页脚 -->
    <footer class="footer">
      <div class="footer-content">
        <el-row :gutter="40">
          <el-col :xs="24" :sm="12" :md="6">
            <div class="footer-brand">
              <img src="/public/images/logo.png" alt="Logo" class="footer-logo">
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

    <!-- 浮动联系按钮 -->
    <ContactFixed
      phone="138-0013-8800"
      email="service@example.com"
      whatsapp="+1 234-567-8900"
    />
  </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();

interface Product {
  id: number;
  name: string;
  desc: string;
  image: string;
  tag: string;
  category: 'tanker' | 'excavator' | 'trailer';
  isNew: boolean;
}

// 商品数据
const products = ref<Product[]>([
  // Tanker Trucks
  {
    id: 1,
    name: 'Three-axle 40,000L 4-compartment Carbon Steel Tanker Truck',
    desc: 'Efficient filtration for engine health. Designed for transporting different types of liquids.',
    image: '/public/products/three-axle-40-000l-4-compartment-carbon-steel20cbe.jpg',
    tag: 'Hot',
    category: 'tanker',
    isNew: false
  },
  {
    id: 2,
    name: '45000 Liters Oil Tanker Trailer',
    desc: 'The volume of the palm oil tanker semi trailer can be customized to reach 30,000-90,000 liters.',
    image: '/public/products/45000LitersOilTankerTrailer.jpg',
    tag: 'New',
    category: 'tanker',
    isNew: true
  },
  {
    id: 3,
    name: 'Two-axle 30,000L Stainless Steel Tanker Truck',
    desc: 'Stainless steel construction for corrosive liquids transportation.',
    image: '/public/products/tanker-truck-5.jpg',
    tag: 'Hot',
    category: 'tanker',
    isNew: false
  },
  {
    id: 4,
    name: 'Four-axle 50,000L Chemical Tanker Truck',
    desc: 'Specialized for transporting hazardous chemicals with safety features.',
    image: '/public/products/tanker-truck-6.jpg',
    tag: 'New',
    category: 'tanker',
    isNew: true
  },
  {
    id: 5,
    name: 'Three-axle 35,000L Fuel Tanker Truck',
    desc: 'Designed for efficient fuel transportation with advanced safety systems.',
    image: '/public/products/tanker-truck-7.jpg',
    tag: 'Hot',
    category: 'tanker',
    isNew: false
  },
  {
    id: 6,
    name: 'Five-axle 60,000L Bulk Cement Tanker Truck',
    desc: 'Specialized for transporting bulk cement with high capacity.',
    image: '/public/products/tanker-truck-8.jpg',
    tag: 'New',
    category: 'tanker',
    isNew: true
  },

  // Excavators
  {
    id: 101,
    name: 'Hydraulic Excavator XG808',
    desc: 'High performance excavator with advanced hydraulic system.',
    image: '/public/products/excavator-1.jpg',
    tag: 'Hot',
    category: 'excavator',
    isNew: false
  },
  {
    id: 102,
    name: 'Mini Excavator XG815',
    desc: 'Compact design for tight spaces and urban construction.',
    image: '/public/products/excavator-2.jpg',
    tag: 'New',
    category: 'excavator',
    isNew: true
  },
  {
    id: 103,
    name: 'Large Mining Excavator XG890',
    desc: 'Heavy-duty excavator for mining operations.',
    image: '/public/products/excavator-3.jpg',
    tag: 'New',
    category: 'excavator',
    isNew: true
  },
  {
    id: 104,
    name: 'Wheel Excavator XG820',
    desc: 'Mobile excavator with excellent maneuverability.',
    image: '/public/products/excavator-4.jpg',
    tag: 'Hot',
    category: 'excavator',
    isNew: false
  },

  // Trailers
  {
    id: 201,
    name: '30,000L Fuel Tanker Trailer',
    desc: 'Lightweight design for efficient fuel transportation.',
    image: '/public/products/trailer-1.jpg',
    tag: 'New',
    category: 'trailer',
    isNew: true
  },
  {
    id: 202,
    name: '40,000L Chemical Tanker Trailer',
    desc: 'Specialized for transporting hazardous chemicals with safety features.',
    image: '/public/products/trailer-2.jpg',
    tag: 'Hot',
    category: 'trailer',
    isNew: false
  },
  {
    id: 203,
    name: 'Flatbed Trailer',
    desc: 'Versatile trailer for transporting various types of cargo.',
    image: '/public/products/trailer-3.jpg',
    tag: 'New',
    category: 'trailer',
    isNew: true
  }
]);

// 状态管理
const currentCategory = ref<'all' | 'tanker' | 'excavator' | 'trailer'>('all');
const searchQuery = ref('');
const sortBy = ref('default');
const currentPage = ref(1);
const pageSize = ref(6);

// 筛选商品
const filteredProducts = computed(() => {
  let result = [...products.value];

  // 分类筛选
  if (currentCategory.value !== 'all') {
    result = result.filter(product => product.category === currentCategory.value);
  }

  // 搜索筛选
  if (searchQuery.value) {
    const query = searchQuery.value.toLowerCase();
    result = result.filter(product =>
      product.name.toLowerCase().includes(query) ||
      product.desc.toLowerCase().includes(query)
    );
  }

  // 排序
  switch (sortBy.value) {
    case 'newest':
      result.sort((a, b) => (b.isNew ? 1 : 0) - (a.isNew ? 1 : 0));
      break;
    default:
      // 默认排序（按ID）
      result.sort((a, b) => a.id - b.id);
  }

  return result;
});

// 分页商品
const paginatedProducts = computed(() => {
  const start = (currentPage.value - 1) * pageSize.value;
  const end = start + pageSize.value;
  return filteredProducts.value.slice(start, end);
});

// 设置分类
const setCategory = (category: 'all' | 'tanker' | 'excavator' | 'trailer') => {
  currentCategory.value = category;
  currentPage.value = 1; // 重置页码
};

// 处理搜索
const handleSearch = () => {
  currentPage.value = 1; // 重置页码
};

// 处理排序
const handleSort = () => {
  currentPage.value = 1; // 重置页码
};

// 处理分页大小变化
const handleSizeChange = (size: number) => {
  pageSize.value = size;
  currentPage.value = 1; // 重置页码
};

// 处理页码变化
const handleCurrentChange = (page: number) => {
  currentPage.value = page;
};

// 重置筛选
const resetFilters = () => {
  currentCategory.value = 'all';
  searchQuery.value = '';
  sortBy.value = 'default';
  currentPage.value = 1;
};

const goToProduct = (id: number, category: 'tanker' | 'excavator' | 'trailer') => {
  if (category === 'tanker') {
    router.push(`/truck/${id}`)
  } else if (category === 'excavator') {
    router.push(`/excavator/${id}`)
  } else if (category === 'trailer') {
    router.push(`/truck/${id}`)
  }
};

// 页面加载时的处理
onMounted(() => {
  // 可以在这里添加初始化逻辑，比如从API获取商品数据
});
</script>

<style scoped>
.products-container {
  position: relative;
  width: 100%;
  user-select: none;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
}

/* 深色主题全局样式覆盖 */
.dark-theme {
  --el-color-primary: #409EFF;
  --el-bg-color: #000000;
  --el-text-color-primary: #ffffff;
  --el-text-color-regular: #8892b0;
  --el-border-color: #2a3a5c;
  --el-fill-color-blank: rgba(22, 33, 62, 0.8);
  --el-fill-color-light: rgba(22, 33, 62, 0.9);
  --el-fill-color-lighter: rgba(22, 33, 62, 0.95);
  --el-mask-color: rgba(0, 0, 0, 0.5);
}

.dark-theme :deep(.el-input__wrapper) {
  background-color: var(--el-fill-color-blank) !important;
  border: 2px solid var(--el-border-color) !important;
  border-radius: 12px !important;
  box-shadow: none !important;
  transition: all 0.3s ease !important;
}

.dark-theme :deep(.el-input__wrapper:hover) {
  border-color: var(--el-color-primary) !important;
  background-color: var(--el-fill-color-light) !important;
}

.dark-theme :deep(.el-input__wrapper.is-focus) {
  border-color: var(--el-color-primary) !important;
  background-color: var(--el-fill-color-lighter) !important;
  box-shadow: 0 0 0 3px rgba(64, 158, 255, 0.1) !important;
}

.dark-theme :deep(.el-input__inner) {
  color: var(--el-text-color-primary) !important;
  font-size: 15px !important;
  font-weight: 500 !important;
  background-color: transparent !important;
}

.dark-theme :deep(.el-input__inner::placeholder) {
  color: var(--el-text-color-regular) !important;
}

.dark-theme :deep(.el-input__prefix-inner) {
  color: var(--el-color-primary) !important;
}

.dark-theme :deep(.el-select .el-input__wrapper) {
  background-color: var(--el-fill-color-blank) !important;
  border: 2px solid var(--el-border-color) !important;
  border-radius: 12px !important;
  box-shadow: none !important;
  transition: all 0.3s ease !important;
  height: 56px !important;
}

.dark-theme :deep(.el-select .el-input__wrapper:hover) {
  border-color: var(--el-color-primary) !important;
  background-color: var(--el-fill-color-light) !important;
}

.dark-theme :deep(.el-select.is-focus .el-input__wrapper) {
  border-color: var(--el-color-primary) !important;
  background-color: var(--el-fill-color-lighter) !important;
  box-shadow: 0 0 0 3px rgba(64, 158, 255, 0.1) !important;
}

.dark-theme :deep(.el-select .el-input__inner) {
  color: var(--el-text-color-primary) !important;
  font-size: 15px !important;
  font-weight: 500 !important;
  background-color: transparent !important;
}

.dark-theme :deep(.el-select .el-input__inner::placeholder) {
  color: var(--el-text-color-regular) !important;
}

.dark-theme :deep(.el-select .el-input__suffix-inner) {
  color: var(--el-color-primary) !important;
}

.dark-theme :deep(.el-select .el-input__prefix-inner) {
  color: var(--el-text-color-regular) !important;
}

.dark-theme :deep(.el-popper) {
  background: rgba(22, 33, 62, 0.98) !important;
  border: 1px solid var(--el-border-color) !important;
  border-radius: 12px !important;
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.4) !important;
  margin-top: 8px !important;
  padding: 8px !important;
}

.dark-theme :deep(.el-popper .el-select-dropdown__item) {
  color: var(--el-text-color-regular) !important;
  padding: 12px 20px !important;
  border-radius: 8px !important;
  margin: 4px 0 !important;
  transition: all 0.2s ease !important;
}

.dark-theme :deep(.el-popper .el-select-dropdown__item:hover) {
  background: rgba(64, 158, 255, 0.15) !important;
  color: var(--el-color-primary) !important;
  transform: translateX(4px) !important;
}

.dark-theme :deep(.el-popper .el-select-dropdown__item.selected) {
  background: rgba(64, 158, 255, 0.2) !important;
  color: var(--el-color-primary) !important;
  font-weight: 600 !important;
}

.dark-theme :deep(.el-popper .el-select-dropdown__item.selected::after) {
  content: '✓';
  float: right;
  color: var(--el-color-primary);
  font-weight: bold;
}

.dark-theme :deep(.el-button--primary) {
  background-color: var(--el-color-primary) !important;
  border: none !important;
  color: #ffffff !important;
  font-weight: 500 !important;
}

.dark-theme :deep(.el-button--primary:hover) {
  background-color: #66b1ff !important;
  transform: translateY(-2px);
  box-shadow: 0 4px 15px rgba(64, 158, 255, 0.4);
}

.dark-theme :deep(.el-pagination__page-btn) {
  background: var(--el-fill-color-blank) !important;
  border: 1px solid var(--el-border-color) !important;
  color: var(--el-text-color-regular) !important;
  border-radius: 8px !important;
  margin: 0 4px !important;
  width: 40px !important;
  height: 40px !important;
  line-height: 40px !important;
  font-weight: 500 !important;
  transition: all 0.3s ease !important;
}

.dark-theme :deep(.el-pagination__page-btn:hover) {
  background: rgba(64, 158, 255, 0.15) !important;
  border-color: var(--el-color-primary) !important;
  color: var(--el-color-primary) !important;
  transform: translateY(-2px);
}

.dark-theme :deep(.el-pagination__page-btn.is-active) {
  background: var(--el-color-primary) !important;
  border-color: var(--el-color-primary) !important;
  color: #ffffff !important;
  font-weight: 600 !important;
}

.dark-theme :deep(.el-pagination__prev),
.dark-theme :deep(.el-pagination__next) {
  background: var(--el-fill-color-blank) !important;
  border: 1px solid var(--el-border-color) !important;
  color: var(--el-text-color-regular) !important;
  border-radius: 8px !important;
  width: 40px !important;
  height: 40px !important;
  line-height: 40px !important;
  transition: all 0.3s ease !important;
}

.dark-theme :deep(.el-pagination__prev:hover),
.dark-theme :deep(.el-pagination__next:hover) {
  background: rgba(64, 158, 255, 0.15) !important;
  border-color: var(--el-color-primary) !important;
  color: var(--el-color-primary) !important;
  transform: translateY(-2px);
}

.dark-theme :deep(.el-pagination__prev:disabled),
.dark-theme :deep(.el-pagination__next:disabled) {
  background: rgba(22, 33, 62, 0.5) !important;
  border-color: var(--el-border-color) !important;
  color: #4a5568 !important;
  cursor: not-allowed !important;
}

.dark-theme :deep(.el-pagination__sizes) {
  color: var(--el-text-color-regular) !important;
}

.dark-theme :deep(.el-pagination__total) {
  color: var(--el-text-color-regular) !important;
}

.dark-theme :deep(.el-pagination__jump) {
  color: var(--el-text-color-regular) !important;
}

.dark-theme :deep(.el-pagination__jump .el-input__wrapper) {
  background: var(--el-fill-color-blank) !important;
  border: 1px solid var(--el-border-color) !important;
  border-radius: 8px !important;
  width: 60px !important;
  height: 40px !important;
}

.dark-theme :deep(.el-pagination__jump .el-input__inner) {
  color: var(--el-text-color-primary) !important;
  text-align: center !important;
}


.floating-topbar {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  z-index: 100;
}

.page-header {
  background: #000;
  padding: 120px 0 80px;
  border-bottom: 1px solid #2a3a5c;
}

.page-header-inner {
  max-width: 1400px;
  margin: 0 auto;
  padding: 0 40px;
  text-align: center;
}

.page-title {
  font-size: 48px;
  font-weight: 700;
  color: #ffffff;
  margin: 0 0 20px 0;
  line-height: 1.2;
}

.page-description {
  font-size: 18px;
  color: #8892b0;
  margin: 0;
  max-width: 800px;
  margin: 0 auto;
  line-height: 1.7;
}

.category-filter-section {
  background: #000;
  padding: 40px 0;
  border-bottom: 1px solid #2a3a5c;
}

.category-filter-inner {
  max-width: 1400px;
  margin: 0 auto;
  padding: 0 40px;
}

 /* Element Plus 深色主题全局样式 */
:deep(.el-popper) {
  background: rgba(22, 33, 62, 0.98) !important;
  border: 1px solid #2a3a5c !important;
  border-radius: 12px !important;
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.4) !important;
  margin-top: 8px !important;
  padding: 8px !important;
}

:deep(.el-popper .el-select-dropdown__item) {
  color: #8892b0 !important;
  padding: 12px 20px !important;
  border-radius: 8px !important;
  margin: 4px 0 !important;
  transition: all 0.2s ease !important;
}

:deep(.el-popper .el-select-dropdown__item:hover) {
  background: rgba(64, 158, 255, 0.15) !important;
  color: #409EFF !important;
  transform: translateX(4px) !important;
}

:deep(.el-popper .el-select-dropdown__item.selected) {
  background: rgba(64, 158, 255, 0.2) !important;
  color: #409EFF !important;
  font-weight: 600 !important;
}

.filter-tabs {
  display: flex;
  gap: 20px;
  flex-wrap: wrap;
}
.filter-tab {
  padding: 16px 32px;
  background: rgba(22, 33, 62, 0.8);
  border: 2px solid #2a3a5c;
  border-radius: 16px;
  cursor: pointer;
  transition: all 0.3s ease;
  white-space: nowrap;
}

.filter-tab:hover {
  background: rgba(64, 158, 255, 0.15);
  border-color: #409EFF;
  transform: translateY(-3px);
}

.filter-tab.active {
  background: rgba(64, 158, 255, 0.2);
  border-color: #409EFF;
  box-shadow: 0 4px 20px rgba(64, 158, 255, 0.3);
}

.tab-name {
  font-size: 16px;
  font-weight: 600;
  color: #ffffff;
}

.search-sort-section {
  display: flex;
  gap: 20px;
  align-items: center;
  flex-wrap: wrap;
  margin-bottom: 40px;
}

.search-box {
  flex: 1;
  min-width: 300px;
}

.search-input {
  height: 56px;
  border-radius: 12px;
  background: rgba(22, 33, 62, 0.8);
  border: 2px solid #2a3a5c;
  color: #ffffff;
}

:deep(.el-input__wrapper) {
  background: rgba(22, 33, 62, 0.8) !important;
  border: 2px solid #2a3a5c !important;
  border-radius: 12px !important;
  box-shadow: none !important;
  transition: all 0.3s ease !important;
}

:deep(.el-input__wrapper:hover) {
  border-color: #409EFF !important;
  background: rgba(22, 33, 62, 0.9) !important;
}

:deep(.el-input__wrapper.is-focus) {
  border-color: #409EFF !important;
  background: rgba(22, 33, 62, 0.95) !important;
  box-shadow: 0 0 0 3px rgba(64, 158, 255, 0.1) !important;
}

:deep(.el-input__inner) {
  color: #ffffff !important;
  font-size: 15px !important;
  font-weight: 500 !important;
}

:deep(.el-input__inner::placeholder) {
  color: #8892b0 !important;
}

:deep(.el-input__prefix-inner) {
  color: #409EFF !important;
}

.sort-box {
  min-width: 200px;
}

.sort-select {
  width: 100%;
  height: 56px !important;
  border-radius: 12px;
  background: rgba(22, 33, 62, 0.8);
  border: 2px solid #2a3a5c;
  color: #ffffff;
}

.sort-select :deep(.el-input) {
  height: 100%;
}

.sort-select :deep(.el-input__wrapper) {
  height: 56px !important;
  min-height: 56px !important;
  max-height: 56px !important;
  line-height: 56px;
}

.sort-select :deep(.el-select__wrapper) {
  height: 56px !important;
  min-height: 56px !important;
  max-height: 56px !important;
}

:deep(.el-select .el-input__wrapper) {
  background-color: rgba(22, 33, 62, 0.8) !important;
  border: 2px solid #2a3a5c !important;
  border-radius: 12px !important;
  box-shadow: none !important;
  transition: all 0.3s ease !important;
  height: 56px !important;
}

:deep(.el-select .el-input__wrapper:hover) {
  border-color: #409EFF !important;
  background-color: rgba(22, 33, 62, 0.9) !important;
}

:deep(.el-select.is-focus .el-input__wrapper) {
  border-color: #409EFF !important;
  background-color: rgba(22, 33, 62, 0.95) !important;
  box-shadow: 0 0 0 3px rgba(64, 158, 255, 0.1) !important;
}

:deep(.el-select .el-input__inner) {
  color: #ffffff !important;
  font-size: 15px !important;
  font-weight: 500 !important;
  background-color: transparent !important;
}

:deep(.el-select .el-input__inner::placeholder) {
  color: #8892b0 !important;
}

:deep(.el-select .el-input__suffix-inner) {
  color: #409EFF !important;
}

:deep(.el-select .el-input__prefix-inner) {
  color: #8892b0 !important;
}

:deep(.el-select-dropdown) {
  background: rgba(22, 33, 62, 0.98) !important;
  border: 1px solid #2a3a5c !important;
  border-radius: 12px !important;
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.4) !important;
  margin-top: 8px !important;
  padding: 8px !important;
}

:deep(.el-select-dropdown__item) {
  color: #8892b0 !important;
  padding: 12px 20px !important;
  border-radius: 8px !important;
  margin: 4px 0 !important;
  transition: all 0.2s ease !important;
}

:deep(.el-select-dropdown__item:hover) {
  background: rgba(64, 158, 255, 0.15) !important;
  color: #409EFF !important;
  transform: translateX(4px) !important;
}

:deep(.el-select-dropdown__item.selected) {
  background: rgba(64, 158, 255, 0.2) !important;
  color: #409EFF !important;
  font-weight: 600 !important;
}

:deep(.el-select-dropdown__item.selected::after) {
  content: '✓';
  float: right;
  color: #409EFF;
  font-weight: bold;
}

.products-section {
  padding: 80px 0;
  background: #000;
}

.products-inner {
  max-width: 1400px;
  margin: 0 auto;
  padding: 0 40px;
}

.products-grid {
  display: flex;
  flex-wrap: wrap;
  gap: 24px;
  justify-content: flex-start;
  margin-bottom: 60px;
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
    margin-left: 0;
  }
}

.product-card {
  background: #16213e;
  border: 1px solid #2a3a5c;
  border-radius: 16px;
  overflow: hidden;
  transition: all 0.3s ease;
  height: 100%;
  display: flex;
  flex-direction: column;
}

.product-card:hover {
  transform: translateY(-8px);
  box-shadow: 0 12px 40px rgba(64, 158, 255, 0.2);
  border-color: #409EFF;
}

.product-image {
  position: relative;
  width: 95%;
  height: 200px;
  margin: 16px auto 0;
  padding: 3px;
  background: linear-gradient(135deg, #0f1626 0%, #1a2744 100%);
  overflow: hidden;
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
  padding: 20px 16px;
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
  margin: 0 0 12px 0;
  line-height: 1.4;
  height: 48px;
  overflow: hidden;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}

.product-info {
  flex-grow: 1;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.product-desc {
  font-size: 13px;
  color: #8892b0;
  margin: 0 0 16px 0;
  line-height: 1.5;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}

.product-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 16px;
}

.product-footer .el-button {
  font-size: 14px;
  padding: 8px 20px;
  border-radius: 8px;
  background: #409EFF;
  border: none;
  transition: all 0.3s ease;
  color: #ffffff !important;
  font-weight: 500 !important;
}

.product-footer .el-button:hover {
  background: #66b1ff !important;
  transform: translateY(-2px);
  box-shadow: 0 4px 15px rgba(64, 158, 255, 0.4);
  color: #ffffff !important;
}

.no-products {
  text-align: center;
  padding: 100px 20px;
}

.no-products-icon {
  font-size: 80px;
  color: #409EFF;
  margin-bottom: 30px;
  opacity: 0.5;
}

.no-products-title {
  font-size: 24px;
  font-weight: 600;
  color: #ffffff;
  margin: 0 0 15px 0;
}

.no-products-desc {
  font-size: 16px;
  color: #8892b0;
  margin: 0 0 30px 0;
}

.no-products .el-button {
  background: #409EFF !important;
  border: none !important;
  color: #ffffff !important;
  font-weight: 500 !important;
  padding: 12px 32px !important;
  border-radius: 8px !important;
  transition: all 0.3s ease !important;
}

.no-products .el-button:hover {
  background: #66b1ff !important;
  transform: translateY(-2px);
  box-shadow: 0 4px 15px rgba(64, 158, 255, 0.4);
}

.pagination-section {
  display: flex;
  justify-content: center;
  margin-top: 60px;
}

.pagination {
  display: flex;
  justify-content: center;
}

:deep(.el-pagination__page-btn) {
  background: rgba(22, 33, 62, 0.8) !important;
  border: 1px solid #2a3a5c !important;
  color: #8892b0 !important;
  border-radius: 8px !important;
  margin: 0 4px !important;
  width: 40px !important;
  height: 40px !important;
  line-height: 40px !important;
  font-weight: 500 !important;
  transition: all 0.3s ease !important;
}

:deep(.el-pagination__page-btn:hover) {
  background: rgba(64, 158, 255, 0.15) !important;
  border-color: #409EFF !important;
  color: #409EFF !important;
  transform: translateY(-2px);
}

:deep(.el-pagination__page-btn.is-active) {
  background: #409EFF !important;
  border-color: #409EFF !important;
  color: #ffffff !important;
  font-weight: 600 !important;
}

:deep(.el-pagination__prev),
:deep(.el-pagination__next) {
  background: rgba(22, 33, 62, 0.8) !important;
  border: 1px solid #2a3a5c !important;
  color: #8892b0 !important;
  border-radius: 8px !important;
  width: 40px !important;
  height: 40px !important;
  line-height: 40px !important;
  transition: all 0.3s ease !important;
}

:deep(.el-pagination__prev:hover),
:deep(.el-pagination__next:hover) {
  background: rgba(64, 158, 255, 0.15) !important;
  border-color: #409EFF !important;
  color: #409EFF !important;
  transform: translateY(-2px);
}

:deep(.el-pagination__prev:disabled),
:deep(.el-pagination__next:disabled) {
  background: rgba(22, 33, 62, 0.5) !important;
  border-color: #2a3a5c !important;
  color: #4a5568 !important;
  cursor: not-allowed !important;
}

:deep(.el-pagination__sizes) {
  color: #8892b0 !important;
}

:deep(.el-pagination__total) {
  color: #8892b0 !important;
}

:deep(.el-pagination__jump) {
  color: #8892b0 !important;
}

:deep(.el-pagination__jump .el-input__wrapper) {
  background: rgba(22, 33, 62, 0.8) !important;
  border: 1px solid #2a3a5c !important;
  border-radius: 8px !important;
  width: 60px !important;
  height: 40px !important;
}

:deep(.el-pagination__jump .el-input__inner) {
  color: #ffffff !important;
  text-align: center !important;
}

:deep(.el-popper) {
  background: rgba(22, 33, 62, 0.98) !important;
  border: 1px solid #2a3a5c !important;
  border-radius: 12px !important;
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.4) !important;
  margin-top: 8px !important;
  padding: 8px !important;
}

:deep(.el-popper .el-select-dropdown__item) {
  color: #8892b0 !important;
  padding: 12px 20px !important;
  border-radius: 8px !important;
  margin: 4px 0 !important;
  transition: all 0.2s ease !important;
}

:deep(.el-popper .el-select-dropdown__item:hover) {
  background: rgba(64, 158, 255, 0.15) !important;
  color: #409EFF !important;
  transform: translateX(4px) !important;
}

:deep(.el-popper .el-select-dropdown__item.selected) {
  background: rgba(64, 158, 255, 0.2) !important;
  color: #409EFF !important;
  font-weight: 600 !important;
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
  color: #409EFF;
  width: 32px;
  height: 32px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: rgba(64, 158, 255, 0.1);
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

@media (max-width: 768px) {
  .page-header {
    padding: 80px 0 60px;
  }

  .page-title {
    font-size: 36px;
  }

  .page-description {
    font-size: 16px;
  }

  .category-filter-inner,
  .products-inner,
  .page-header-inner {
    padding: 0 20px;
  }

  .filter-tabs {
    gap: 12px;
  }

  .filter-tab {
    padding: 12px 24px;
  }

  .tab-name {
    font-size: 14px;
  }

  .search-sort-section {
    flex-direction: column;
    align-items: stretch;
  }

  .search-box,
  .sort-box {
    min-width: 100%;
  }

  .products-section {
    padding: 40px 0;
  }

  .product-col {
    width: calc(50% - 12px);
  }

  .product-image {
    height: 160px;
  }

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

@media (max-width: 480px) {
  .product-col {
    width: 100%;
  }

  .page-title {
    font-size: 28px;
  }

  .filter-tabs {
    justify-content: center;
  }

  .filter-tab {
    padding: 10px 20px;
  }

  .tab-name {
    font-size: 12px;
  }
}
</style>
