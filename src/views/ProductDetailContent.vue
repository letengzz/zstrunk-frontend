<template>
    <TopBar class="floating-topbar" />
  <div class="product-detail-container">
    <div class="breadcrumb-section">
      <div class="breadcrumb-inner">
        <el-breadcrumb separator="/">
          <el-breadcrumb-item><a href="/">Home</a></el-breadcrumb-item>
          <el-breadcrumb-item><a href="/">Products</a></el-breadcrumb-item>
          <el-breadcrumb-item><a href="/">{{ categoryTitle }}</a></el-breadcrumb-item>
          <el-breadcrumb-item>{{ product?.name }}</el-breadcrumb-item>
        </el-breadcrumb>
      </div>
    </div>

    <div class="product-detail-section">
      <div class="product-detail-inner">
        <div class="product-image-container">
          <div class="main-image">
            <img :src="selectedImage" :alt="product?.name" class="product-main-img">
          </div>
          <div class="thumbnail-images">
            <div
              class="thumbnail-item"
              :class="{ active: selectedImageIndex === index }"
              v-for="(image, index) in productImages"
              :key="index"
              @click="selectImage(index)"
            >
              <img :src="image" :alt="`${product?.name} - ${index + 1}`" class="thumbnail-img">
            </div>
          </div>
        </div>

        <div class="product-info-container">
          <div class="product-tag">{{ product?.tag }}</div>
          <h1 class="product-title">{{ product?.name }}</h1>
          <p class="product-description">{{ product?.desc }}</p>

          <div class="product-specs" v-if="product?.specs">
            <h3 class="specs-title">Specifications</h3>
            <div class="specs-grid">
              <div class="spec-item" v-for="(spec, index) in product.specs" :key="index">
                <span class="spec-label">{{ spec.label }}:</span>
                <span class="spec-value">{{ spec.value }}</span>
              </div>
            </div>
          </div>

          <div class="product-params" v-if="product?.parameters">
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
              <i class="i-ep-phone" w25 h25 m10></i> Contact Us
            </el-button>
            <el-button type="default" size="large" class="btn-secondary">
              <i class="i-ep-message" w25 h25 m10></i> Send Inquiry
            </el-button>
          </div>
        </div>
      </div>
    </div>

    <div class="product-description-section">
      <div class="product-description-inner">
        <h2 class="section-title">Product Details</h2>
        <div class="description-content">
          <p>{{ product?.detailedDescription }}</p>
          <div class="feature-list" v-if="product?.features">
            <h3 class="feature-list-title">Key Features</h3>
            <ul>
              <li v-for="(feature, index) in product.features" :key="index">
                <i class="i-ep-check"></i> {{ feature }}
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>

    <div class="related-products-section">
      <div class="related-products-inner">
        <h2 class="section-title">Related Products</h2>
        <div class="related-products-grid">
          <div class="related-product-item" v-for="relatedProduct in relatedProducts" :key="relatedProduct.id">
            <el-card class="product-card" :body-style="{ padding: '0px', height: '100%' }">
              <div class="product-image">
                <img :src="relatedProduct.image" class="product-img" />
                <div class="product-tag">{{ relatedProduct.tag }}</div>
              </div>
              <div class="product-content">
                <h3 class="product-name">{{ relatedProduct.name }}</h3>
                <div class="product-info">
                  <p class="product-desc">{{ relatedProduct.desc }}</p>
                  <div class="product-footer">
                    <el-button type="primary" round>View More</el-button>
                  </div>
                </div>
              </div>
            </el-card>
          </div>
        </div>
      </div>
    </div>

    <Footer />
    <ContactFixed
      phone="138-0013-8800"
      email="service@example.com"
      whatsapp="+1 234-567-8900"
    />
  </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { getProductById, getProductsByCategory } from '@/data/products'
import Footer from '@/components/Footer.vue'
import ContactFixed from '@/components/ContactFixed.vue'

const props = defineProps<{
  id: number
  category: 'tanker' | 'excavator'
}>()

const router = useRouter()
const product = ref(getProductById(props.id))
const selectedImageIndex = ref(0)

const categoryTitle = computed(() => {
  return props.category === 'tanker' ? 'Tanker Trucks' : 'Excavators'
})

const productImages = computed(() => {
  if (!product.value) return []
  return product.value.images || [product.value.image]
})

const selectedImage = computed(() => {
  return productImages.value[selectedImageIndex.value] || ''
})

const relatedProducts = computed(() => {
  if (!product.value) return []
  const allProducts = getProductsByCategory(props.category)
  return allProducts
    .filter(p => p.id !== product.value?.id)
    .slice(0, 3)
})

const selectImage = (index: number) => {
  selectedImageIndex.value = index
}

onMounted(() => {
  if (!product.value) {
    router.push('/')
  }
})
</script>

<style scoped>
.floating-topbar {
background: linear-gradient(180deg, rgba(26, 42, 74, 0.6) 0%, rgba(26, 42, 74, 0.4) 50%, rgba(139, 0, 0, 0.4) 100%);
  /* background: rgba(255, 255, 255, 0.1);
  backdrop-filter: blur(10px);
  -webkit-backdrop-filter: blur(10px); */
}
.product-detail-container {
  position: relative;
  width: 100%;
  user-select: none;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
}

.breadcrumb-section {
  background: #ffffff;
  padding: 20px 0;
  border-bottom: 1px solid #e2e8f0;
}

.breadcrumb-inner {
  max-width: 1400px;
  margin: 0 auto;
  padding: 0 40px;
}

:deep(.el-breadcrumb__item:last-child .el-breadcrumb__inner) {
  color: #FF0000;
}

:deep(.el-breadcrumb__inner a) {
  color: #4a5568;
  text-decoration: none;
  transition: all 0.3s ease;
}

:deep(.el-breadcrumb__inner a:hover) {
  color: #FF0000;
}

.product-detail-section {
  padding: 60px 0;
  background: #ffffff;
}

.product-detail-inner {
  max-width: 1400px;
  margin: 0 auto;
  padding: 0 40px;
  display: flex;
  gap: 60px;
  align-items: flex-start;
}

.product-image-container {
  flex: 1;
  max-width: 600px;
}

.main-image {
  position: relative;
  background: #F6F5ED;
  border-radius: 16px;
  padding: 20px;
  margin-bottom: 20px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.06);
  border: 1px solid #e2e8f0;
  overflow: hidden;
}

.product-main-img {
  width: 100%;
  height: auto;
  border-radius: 12px;
  object-fit: cover;
}

.thumbnail-images {
  display: flex;
  gap: 12px;
  overflow-x: auto;
  padding-bottom: 10px;
}

.thumbnail-item {
  flex: 0 0 auto;
  width: 100px;
  height: 100px;
  background: #F6F5ED;
  border-radius: 8px;
  padding: 8px;
  cursor: pointer;
  transition: all 0.3s ease;
  border: 2px solid transparent;
  overflow: hidden;
}

.thumbnail-item:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 15px rgba(255, 0, 0, 0.15);
  border-color: rgba(255, 0, 0, 0.3);
}

.thumbnail-item.active {
  border-color: #FF0000;
  box-shadow: 0 4px 15px rgba(255, 0, 0, 0.2);
}

.thumbnail-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 6px;
}

.product-info-container {
  flex: 1;
  max-width: 600px;
}

.product-tag {
  display: inline-block;
  padding: 4px 16px;
  background: #FF0000;
  color: #ffffff;
  font-size: 14px;
  font-weight: 500;
  border-radius: 20px;
  margin-bottom: 20px;
}

.product-title {
  font-size: 32px;
  font-weight: 700;
  color: #1a2a4a;
  margin: 0 0 20px 0;
  line-height: 1.2;
}

.product-description {
  font-size: 16px;
  color: #4a5568;
  margin: 0 0 40px 0;
  line-height: 1.7;
}

.product-specs {
  margin-bottom: 40px;
}

.specs-title {
  font-size: 20px;
  font-weight: 600;
  color: #1a2a4a;
  margin: 0 0 20px 0;
  position: relative;
  padding-bottom: 12px;
}

.specs-title::after {
  content: '';
  position: absolute;
  bottom: 0;
  left: 0;
  width: 40px;
  height: 3px;
  background: linear-gradient(90deg, #FF0000, transparent);
  border-radius: 2px;
}

.specs-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 16px;
  background: #F6F5ED;
  padding: 24px;
  border-radius: 16px;
  border: 1px solid #e2e8f0;
}

.spec-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.spec-label {
  font-size: 14px;
  color: #4a5568;
  font-weight: 500;
}

.spec-value {
  font-size: 14px;
  color: #1a2a4a;
  font-weight: 600;
}

.product-params {
  margin-bottom: 40px;
}

.params-title {
  font-size: 20px;
  font-weight: 600;
  color: #1a2a4a;
  margin: 0 0 20px 0;
  position: relative;
  padding-bottom: 12px;
}

.params-title::after {
  content: '';
  position: absolute;
  bottom: 0;
  left: 0;
  width: 40px;
  height: 3px;
  background: linear-gradient(90deg, #FF0000, transparent);
  border-radius: 2px;
}

.params-table {
  background: #F6F5ED;
  padding: 24px;
  border-radius: 16px;
  border: 1px solid #e2e8f0;
}

.param-row {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 12px 0;
  border-bottom: 1px solid #e2e8f0;
}

.param-row:last-child {
  border-bottom: none;
}

.param-name {
  font-size: 14px;
  color: #4a5568;
  font-weight: 500;
}

.param-value {
  font-size: 14px;
  color: #1a2a4a;
  font-weight: 600;
}

.action-buttons {
  display: flex;
  gap: 20px;
  margin-top: 40px;
}

.btn-primary {
  flex: 1;
  height: 56px;
  font-size: 16px;
  font-weight: 600;
  border-radius: 12px;
  background: #FF0000;
  border: none;
  transition: all 0.3s ease;
}

.btn-primary:hover {
  background: #B22222;
  transform: translateY(-3px);
  box-shadow: 0 8px 25px rgba(255, 0, 0, 0.35);
}

.btn-secondary {
  flex: 1;
  height: 56px;
  font-size: 16px;
  font-weight: 600;
  border-radius: 12px;
  background: #ffffff;
  border: 2px solid #e2e8f0;
  color: #1a2a4a;
  transition: all 0.3s ease;
}

.btn-secondary:hover {
  background: #FF0000;
  border-color: #FF0000;
  color: #ffffff;
  transform: translateY(-3px);
  box-shadow: 0 8px 25px rgba(255, 0, 0, 0.35);
}

.product-description-section {
  padding: 80px 0;
  background: #F6F5ED;
  border-top: 1px solid #e2e8f0;
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
  position: relative;
  padding-bottom: 16px;
}

.section-title::after {
  content: '';
  position: absolute;
  bottom: 0;
  left: 50%;
  transform: translateX(-50%);
  width: 60px;
  height: 3px;
  background: linear-gradient(90deg, transparent, #FF0000, transparent);
  border-radius: 2px;
}

.description-content {
  background: #ffffff;
  padding: 40px;
  border-radius: 16px;
  border: 1px solid #e2e8f0;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.06);
}

.description-content p {
  font-size: 16px;
  color: #4a5568;
  margin: 0 0 30px 0;
  line-height: 1.7;
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
  font-size: 16px;
  color: #4a5568;
  margin: 0 0 12px 0;
  padding-left: 30px;
  position: relative;
  line-height: 1.5;
}

.feature-list li i {
  position: absolute;
  left: 0;
  top: 2px;
  color: #FF0000;
  font-size: 18px;
}

.related-products-section {
  padding: 80px 0;
  background: #ffffff;
  border-top: 1px solid #e2e8f0;
}

.related-products-inner {
  max-width: 1400px;
  margin: 0 auto;
  padding: 0 40px;
}

.related-products-grid {
  display: flex;
  flex-wrap: wrap;
  gap: 24px;
  justify-content: center;
  margin-top: 40px;
}

.related-product-item {
  width: calc(33.333% - 16px);
}

@media (max-width: 992px) {
  .related-product-item {
    width: calc(50% - 12px);
  }
}

@media (max-width: 768px) {
  .related-product-item {
    width: calc(50% - 12px);
  }
}

@media (max-width: 480px) {
  .related-product-item {
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
  height: 170px;
  margin: 16px auto 0;
  padding: 3px;
  background: linear-gradient(135deg, #f5f5f5 0%, #e8e8e8 100%);
  overflow: hidden;
}

.product-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
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
  color: #1a2a4a;
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

@media (max-width: 992px) {
  .product-detail-inner {
    flex-direction: column;
  }

  .product-image-container,
  .product-info-container {
    max-width: 100%;
  }
}
</style>
