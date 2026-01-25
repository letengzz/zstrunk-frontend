<template>
  <div class="product-detail-container" v-if="product">
    <TopBar class="floating-topbar" />

    <div class="breadcrumb-section">
      <div class="breadcrumb-inner">
        <el-breadcrumb separator="/">
          <el-breadcrumb-item><a href="/">Home</a></el-breadcrumb-item>
          <el-breadcrumb-item><a href="/">Products</a></el-breadcrumb-item>
          <el-breadcrumb-item><a :href="`/products?category=${product.category}`">{{ product.category === 'tanker' ? 'Tanker Trucks' : 'Excavators' }}</a></el-breadcrumb-item>
          <el-breadcrumb-item>{{ product.name }}</el-breadcrumb-item>
        </el-breadcrumb>
      </div>
    </div>

    <div class="product-detail-section">
      <div class="product-detail-inner">
        <div class="product-image-container">
          <div class="main-image">
            <img :src="product.image" :alt="product.name" class="product-main-img">
          </div>
          <div class="thumbnail-images" v-if="product.images && product.images.length > 0">
            <div
              class="thumbnail-item"
              :class="{ active: selectedImageIndex === index }"
              v-for="(image, index) in product.images"
              :key="index"
              @click="selectImage(index)"
            >
              <img :src="image" :alt="`${product.name} - ${index + 1}`" class="thumbnail-img">
            </div>
          </div>
        </div>

        <div class="product-info-container">
          <div class="product-tag">{{ product.tag }}</div>
          <h1 class="product-title">{{ product.name }}</h1>
          <p class="product-description">{{ product.desc }}</p>

          <div class="product-specs" v-if="product.specs && product.specs.length > 0">
            <h3 class="specs-title">Specifications</h3>
            <div class="specs-grid">
              <div class="spec-item" v-for="(spec, index) in product.specs" :key="index">
                <span class="spec-label">{{ spec.label }}:</span>
                <span class="spec-value">{{ spec.value }}</span>
              </div>
            </div>
          </div>

          <div class="product-params" v-if="product.parameters && product.parameters.length > 0">
            <h3 class="params-title">Technical Parameters</h3>
            <div class="params-table">
              <div class="param-row" v-for="(param, index) in product.parameters" :key="index">
                <span class="param-name">{{ param.name }}</span>
                <span class="param-value">{{ param.value }}</span>
              </div>
            </div>
          </div>

          <div class="action-buttons">
            <el-button type="primary" size="large" class="btn-primary">
              <span class="btn-icon i-ep-phone"></span> Contact Us
            </el-button>
            <el-button type="default" size="large" class="btn-secondary">
              <span class="btn-icon i-ep-message"></span> Send Inquiry
            </el-button>
          </div>
        </div>
      </div>
    </div>

    <div class="product-description-section" v-if="product.detailedDescription">
      <div class="product-description-inner">
        <h2 class="section-title">Product Details</h2>
        <div class="description-content">
          <p>{{ product.detailedDescription }}</p>
          <div class="feature-list" v-if="product.features && product.features.length > 0">
            <h3 class="feature-list-title">Key Features</h3>
            <ul>
              <li v-for="(feature, index) in product.features" :key="index">
                <span class="feature-icon">✓</span> {{ feature }}
              </li>
            </ul>
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
  <div class="not-found" v-else>
    <h1>Product Not Found</h1>
    <p>The product you are looking for does not exist.</p>
    <el-button type="primary" @click="$router.push('/products')">Back to Products</el-button>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import TopBar from '@/components/TopBar.vue'
import Footer from '@/components/Footer.vue'
import ContactFixed from '@/components/ContactFixed.vue'
import { getProductById } from '@/data/products'

const route = useRoute() as { params: { id?: string } }
const router = useRouter()

const productId = computed(() => {
  const id = route.params.id
  return id ? parseInt(id) : 0
})

const product = computed(() => getProductById(productId.value))

const selectedImageIndex = ref(0)

computed(() => {
  if (product.value?.images && product.value.images.length > 0) {
    return product.value.images[selectedImageIndex.value]
  }
  return product.value?.image
})

function selectImage(index: number) {
  selectedImageIndex.value = index
}

onMounted(() => {
  if (!product.value) {
    router.push('/products')
  }
})
</script>

<style scoped>
.product-detail-container {
  min-height: 100vh;
  background: #F6F5ED;
}

.floating-topbar {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 1000;
}

.breadcrumb-section {
  background: #ffffff;
  padding: 20px 0;
  border-bottom: 1px solid #e0ddd5;
}

.breadcrumb-inner {
  max-width: 1400px;
  margin: 0 auto;
  padding: 0 40px;
}

.product-detail-section {
  padding: 40px 0 80px;
}

.product-detail-inner {
  max-width: 1400px;
  margin: 0 auto;
  padding: 0 40px;
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 60px;
}

.product-image-container {
  position: sticky;
  top: 100px;
  height: fit-content;
}

.main-image {
  background: #ffffff;
  border-radius: 20px;
  padding: 20px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
  margin-bottom: 20px;
}

.product-main-img {
  width: 100%;
  height: auto;
  border-radius: 12px;
}

.thumbnail-images {
  display: flex;
  gap: 12px;
  flex-wrap: wrap;
}

.thumbnail-item {
  width: 80px;
  height: 80px;
  padding: 8px;
  background: #ffffff;
  border: 2px solid transparent;
  border-radius: 12px;
  cursor: pointer;
  transition: all 0.3s ease;
}

.thumbnail-item:hover {
  border-color: #FF0000;
}

.thumbnail-item.active {
  border-color: #FF0000;
}

.thumbnail-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 8px;
}

.product-info-container {
  padding-top: 20px;
}

.product-tag {
  display: inline-block;
  background: linear-gradient(135deg, #8B0000, #FF0000);
  color: #ffffff;
  padding: 6px 16px;
  border-radius: 20px;
  font-size: 12px;
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 1px;
  margin-bottom: 16px;
}

.product-title {
  font-size: 32px;
  font-weight: 700;
  color: #1a2a4a;
  margin: 0 0 20px 0;
  line-height: 1.3;
}

.product-price {
  font-size: 36px;
  font-weight: 700;
  color: #FF0000;
  margin: 0 0 24px 0;
}

.product-description {
  font-size: 16px;
  color: #4a5568;
  line-height: 1.8;
  margin: 0 0 32px 0;
}

.product-specs {
  margin-bottom: 32px;
}

.specs-title,
.params-title {
  font-size: 20px;
  font-weight: 600;
  color: #1a2a4a;
  margin: 0 0 20px 0;
}

.specs-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 16px;
}

.spec-item {
  display: flex;
  justify-content: space-between;
  padding: 12px 16px;
  background: #ffffff;
  border-radius: 10px;
  border: 1px solid #e2e8f0;
}

.spec-label {
  color: #5a6a7a;
  font-size: 14px;
}

.spec-value {
  color: #1a2a4a;
  font-weight: 600;
  font-size: 14px;
}

.product-params {
  margin-bottom: 32px;
}

.params-table {
  background: #ffffff;
  border-radius: 16px;
  overflow: hidden;
  border: 1px solid #e2e8f0;
}

.param-row {
  display: flex;
  padding: 16px 20px;
  border-bottom: 1px solid #e2e8f0;
}

.param-row:last-child {
  border-bottom: none;
}

.param-row:nth-child(odd) {
  background: #fafafa;
}

.param-name {
  width: 200px;
  color: #5a6a7a;
  font-size: 14px;
}

.param-value {
  color: #1a2a4a;
  font-weight: 500;
  font-size: 14px;
}

.action-buttons {
  display: flex;
  gap: 16px;
  margin-top: 40px;
}

.btn-primary {
  background: linear-gradient(135deg, #8B0000, #FF0000) !important;
  border: none !important;
  border-radius: 12px !important;
  padding: 16px 32px !important;
  font-size: 16px !important;
  font-weight: 600 !important;
}

.btn-secondary {
  background: #ffffff !important;
  border: 2px solid #e2e8f0 !important;
  border-radius: 12px !important;
  padding: 16px 32px !important;
  font-size: 16px !important;
  font-weight: 600 !important;
  color: #1a2a4a !important;
}

.btn-icon {
  margin-right: 8px;
}

.product-description-section {
  background: #ffffff;
  padding: 80px 0;
}

.product-description-inner {
  max-width: 1400px;
  margin: 0 auto;
  padding: 0 40px;
}

.section-title {
  font-size: 32px;
  font-weight: 700;
  color: #1a2a4a;
  text-align: center;
  margin: 0 0 40px 0;
}

.description-content {
  max-width: 900px;
  margin: 0 auto;
}

.description-content p {
  font-size: 16px;
  color: #4a5568;
  line-height: 1.8;
  margin: 0 0 32px 0;
}

.feature-list {
  margin-top: 40px;
}

.feature-list-title {
  font-size: 20px;
  font-weight: 600;
  color: #1a2a4a;
  margin: 0 0 20px 0;
}

.feature-list ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.feature-list li {
  display: flex;
  align-items: flex-start;
  gap: 12px;
  padding: 12px 0;
  font-size: 15px;
  color: #4a5568;
}

.feature-icon {
  color: #FF0000;
  font-weight: bold;
  flex-shrink: 0;
}

.not-found {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background: #F6F5ED;
}

.not-found h1 {
  font-size: 48px;
  color: #1a2a4a;
  margin: 0 0 16px 0;
}

.not-found p {
  font-size: 18px;
  color: #5a6a7a;
  margin: 0 0 32px 0;
}

@media (max-width: 992px) {
  .product-detail-inner {
    grid-template-columns: 1fr;
    gap: 40px;
  }

  .product-image-container {
    position: static;
  }

  .specs-grid {
    grid-template-columns: 1fr;
  }

  .param-name {
    width: 150px;
  }
}

@media (max-width: 768px) {
  .breadcrumb-inner,
  .product-detail-inner,
  .product-description-inner {
    padding: 0 20px;
  }

  .product-title {
    font-size: 24px;
  }

  .product-price {
    font-size: 28px;
  }

  .action-buttons {
    flex-direction: column;
  }
}
</style>
