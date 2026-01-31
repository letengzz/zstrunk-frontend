<template>
  <div class="product-detail-container">
    <!-- <TopBar class="floating-topbar" /> -->

    <!-- 面包屑导航 -->
    <div class="breadcrumb-section">
      <div class="breadcrumb-inner">
        <el-breadcrumb separator="/">
          <el-breadcrumb-item><a href="/">Home</a></el-breadcrumb-item>
          <el-breadcrumb-item><a href="/">Products</a></el-breadcrumb-item>
          <el-breadcrumb-item><a href="/">Tanker Trucks</a></el-breadcrumb-item>
          <el-breadcrumb-item>{{ product.name }}</el-breadcrumb-item>
        </el-breadcrumb>
      </div>
    </div>

    <!-- 商品详情部分 -->
    <div class="product-detail-section">
      <div class="product-detail-inner">
        <!-- 商品图片展示 -->
        <div class="product-image-container">
          <div class="main-image">
            <img :src="selectedImage" alt="{{ product.name }}" class="product-main-img">
          </div>
          <div class="thumbnail-images">
            <div
              class="thumbnail-item"
              :class="{ active: selectedImageIndex === index }"
              v-for="(image, index) in product.images"
              :key="index"
              @click="selectImage(index)"
            >
              <img :src="image" alt="{{ product.name }} - {{ index + 1 }}" class="thumbnail-img">
            </div>
          </div>
        </div>

        <!-- 商品信息 -->
        <div class="product-info-container">
          <div class="product-tag">{{ product.tag }}</div>
          <h1 class="product-title">{{ product.name }}</h1>
          <div class="product-price">${{ product.price.toLocaleString() }}</div>
          <p class="product-description">{{ product.description }}</p>

          <!-- 商品规格选择 -->
          <div class="product-specs">
            <h3 class="specs-title">Specifications</h3>
            <div class="specs-grid">
              <div class="spec-item" v-for="(spec, index) in product.specs" :key="index">
                <span class="spec-label">{{ spec.label }}:</span>
                <span class="spec-value">{{ spec.value }}</span>
              </div>
            </div>
          </div>

          <!-- 商品参数 -->
          <div class="product-params">
            <h3 class="params-title">Technical Parameters</h3>
            <div class="params-table">
              <div class="param-row" v-for="(param, index) in product.parameters" :key="index">
                <span class="param-name">{{ param.name }}</span>
                <span class="param-value">{{ param.value }}</span>
              </div>
            </div>
          </div>

          <!-- 操作按钮 -->
          <div class="action-buttons">
            <el-button type="primary" size="large" class="btn-primary">
              <i class="i-ep-phone" w25 h25></i> Contact Us
            </el-button>
            <el-button type="default" size="large" class="btn-secondary">
              <i class="i-ep-message" w25 h25></i> Send Inquiry
            </el-button>
          </div>
        </div>
      </div>
    </div>

    <!-- 商品详情描述 -->
    <div class="product-description-section">
      <div class="product-description-inner">
        <h2 class="section-title">Product Details</h2>
        <div class="description-content">
          <p>{{ product.detailedDescription }}</p>
          <div class="feature-list">
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

    <!-- 相关产品推荐 -->
    <div class="related-products-section">
      <div class="related-products-inner">
        <h2 class="section-title">Related Products</h2>
        <div class="related-products-grid">
          <div class="related-product-item" v-for="(relatedProduct) in relatedProducts" :key="relatedProduct.id">
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
import { ref, computed } from 'vue';

interface ProductSpec {
  label: string;
  value: string;
}

interface ProductParameter {
  name: string;
  value: string;
}

interface Product {
  id: number;
  name: string;
  price: number;
  tag: string;
  description: string;
  detailedDescription: string;
  images: string[];
  specs: ProductSpec[];
  parameters: ProductParameter[];
  features: string[];
}

interface RelatedProduct {
  id: number;
  name: string;
  desc: string;
  price: number;
  image: string;
  tag: string;
}

// 商品数据
const product = ref<Product>({
  id: 1,
  name: 'Three-axle 40,000L 4-compartment Carbon Steel Tanker Truck',
  price: 299000,
  tag: 'Hot',
  description: 'Efficient filtration for engine health. The three-axle tanker truck with 40,000L capacity and 4 compartments is designed for transporting different types of liquids safely and efficiently.',
  detailedDescription: 'The Three-axle 40,000L 4-compartment Carbon Steel Tanker Truck is a high-quality vehicle designed for the transportation of various liquids. It features a robust carbon steel construction that ensures durability and longevity, even under harsh operating conditions. The 4-compartment design allows for the transportation of different types of liquids simultaneously, maximizing efficiency and flexibility. With a total capacity of 40,000 liters, this tanker truck is ideal for medium to large-scale liquid transportation needs. It is equipped with advanced safety features, including anti-rollover protection, emergency shut-off valves, and spill containment systems to ensure safe operation. The truck is also designed for easy maintenance, with accessible components and a user-friendly interface for the driver.',
  images: [
    '/public/products/three-axle-40-000l-4-compartment-carbon-steel20cbe.jpg',
    '/public/products/tanker-truck-2.jpg',
    '/public/products/tanker-truck-3.jpg',
    '/public/products/tanker-truck-4.jpg'
  ],
  specs: [
    { label: 'Capacity', value: '40,000L' },
    { label: 'Compartments', value: '4' },
    { label: 'Material', value: 'Carbon Steel' },
    { label: 'Axles', value: '3' },
    { label: 'Length', value: '12.5m' },
    { label: 'Width', value: '2.5m' },
    { label: 'Height', value: '3.8m' },
    { label: 'Gross Weight', value: '31t' }
  ],
  parameters: [
    { name: 'Engine', value: 'Weichai WP10.375E53' },
    { name: 'Horsepower', value: '375HP' },
    { name: 'Transmission', value: 'Fast 12-speed' },
    { name: 'Drive Type', value: '6×4' },
    { name: 'Tire Size', value: '12R22.5' },
    { name: 'Braking System', value: 'Air brake with ABS' },
    { name: 'Suspension', value: 'Leaf spring' },
    { name: 'Fuel Tank', value: '400L' }
  ],
  features: [
    '4-compartment design for transporting different liquids',
    'Carbon steel construction for durability',
    'Advanced safety features including anti-rollover protection',
    'Emergency shut-off valves and spill containment systems',
    'Easy maintenance with accessible components',
    'User-friendly interface for the driver',
    'Compliant with international safety standards',
    'Customizable options available'
  ]
});

// 相关产品数据
const relatedProducts = ref<RelatedProduct[]>([
  {
    id: 2,
    name: '45000 Liters Oil Tanker Trailer',
    desc: 'The volume of the palm oil tanker semi trailer can be customized to reach 30,000-90,000 liters.',
    price: 599000,
    image: '/public/products/45000LitersOilTankerTrailer.jpg',
    tag: 'New'
  },
  {
    id: 3,
    name: 'Two-axle 30,000L Stainless Steel Tanker Truck',
    desc: 'Stainless steel construction for corrosive liquids transportation.',
    price: 249000,
    image: '/public/products/tanker-truck-5.jpg',
    tag: 'Hot'
  },
  {
    id: 4,
    name: 'Four-axle 50,000L Chemical Tanker Truck',
    desc: 'Specialized for transporting hazardous chemicals with safety features.',
    price: 699000,
    image: '/public/products/tanker-truck-6.jpg',
    tag: 'New'
  }
]);

// 图片选择
const selectedImageIndex = ref(0);
const selectedImage = computed(() => product.value.images[selectedImageIndex.value]);

const selectImage = (index: number) => {
  selectedImageIndex.value = index;
};
</script>

<style scoped>
.product-detail-container {
  position: relative;
  width: 100%;
  user-select: none;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
}

.floating-topbar {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  z-index: 100;
}

.breadcrumb-section {
  background: #000;
  padding: 20px 0;
  border-bottom: 1px solid #2a3a5c;
}

.breadcrumb-inner {
  max-width: 1400px;
  margin: 0 auto;
  padding: 0 40px;
}

:deep(.el-breadcrumb__item:last-child .el-breadcrumb__inner) {
  color: #409EFF;
}

:deep(.el-breadcrumb__inner a) {
  color: #8892b0;
  text-decoration: none;
  transition: all 0.3s ease;
}

:deep(.el-breadcrumb__inner a:hover) {
  color: #409EFF;
}

.product-detail-section {
  padding: 60px 0;
  background: #000;
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
  background: #16213e;
  border-radius: 16px;
  padding: 20px;
  margin-bottom: 20px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.3);
  border: 1px solid #2a3a5c;
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
  background: #16213e;
  border-radius: 8px;
  padding: 8px;
  cursor: pointer;
  transition: all 0.3s ease;
  border: 2px solid transparent;
  overflow: hidden;
}

.thumbnail-item:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 15px rgba(64, 158, 255, 0.2);
  border-color: rgba(64, 158, 255, 0.5);
}

.thumbnail-item.active {
  border-color: #409EFF;
  box-shadow: 0 4px 15px rgba(64, 158, 255, 0.3);
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
  background: #409EFF;
  color: #ffffff;
  font-size: 14px;
  font-weight: 500;
  border-radius: 20px;
  margin-bottom: 20px;
}

.product-title {
  font-size: 32px;
  font-weight: 700;
  color: #ffffff;
  margin: 0 0 20px 0;
  line-height: 1.2;
}

.product-price {
  font-size: 28px;
  font-weight: 700;
  color: #409EFF;
  margin: 0 0 30px 0;
}

.product-description {
  font-size: 16px;
  color: #8892b0;
  margin: 0 0 40px 0;
  line-height: 1.7;
}

.product-specs {
  margin-bottom: 40px;
}

.specs-title {
  font-size: 20px;
  font-weight: 600;
  color: #ffffff;
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
  background: linear-gradient(90deg, #409EFF, transparent);
  border-radius: 2px;
}

.specs-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 16px;
  background: #16213e;
  padding: 24px;
  border-radius: 16px;
  border: 1px solid #2a3a5c;
}

.spec-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.spec-label {
  font-size: 14px;
  color: #8892b0;
  font-weight: 500;
}

.spec-value {
  font-size: 14px;
  color: #ffffff;
  font-weight: 600;
}

.product-params {
  margin-bottom: 40px;
}

.params-title {
  font-size: 20px;
  font-weight: 600;
  color: #ffffff;
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
  background: linear-gradient(90deg, #409EFF, transparent);
  border-radius: 2px;
}

.params-table {
  background: #16213e;
  padding: 24px;
  border-radius: 16px;
  border: 1px solid #2a3a5c;
}

.param-row {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 12px 0;
  border-bottom: 1px solid #2a3a5c;
}

.param-row:last-child {
  border-bottom: none;
}

.param-name {
  font-size: 14px;
  color: #8892b0;
  font-weight: 500;
}

.param-value {
  font-size: 14px;
  color: #ffffff;
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
  background: #409EFF;
  border: none;
  transition: all 0.3s ease;
}

.btn-primary:hover {
  background: #66b1ff;
  transform: translateY(-3px);
  box-shadow: 0 8px 25px rgba(64, 158, 255, 0.4);
}

.btn-secondary {
  flex: 1;
  height: 56px;
  font-size: 16px;
  font-weight: 600;
  border-radius: 12px;
  background: rgba(22, 33, 62, 0.8);
  border: 2px solid #2a3a5c;
  color: #ffffff;
  transition: all 0.3s ease;
}

.btn-secondary:hover {
  background: rgba(64, 158, 255, 0.15);
  border-color: #409EFF;
  transform: translateY(-3px);
  box-shadow: 0 8px 25px rgba(64, 158, 255, 0.2);
  color: #409EFF;
}

.product-description-section {
  padding: 80px 0;
  background: #000;
  border-top: 1px solid #2a3a5c;
}

.product-description-inner {
  max-width: 1400px;
  margin: 0 auto;
  padding: 0 40px;
}

.section-title {
  font-size: 32px;
  font-weight: 700;
  color: #ffffff;
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
  background: linear-gradient(90deg, transparent, #409EFF, transparent);
  border-radius: 2px;
}

.description-content {
  background: #16213e;
  padding: 40px;
  border-radius: 16px;
  border: 1px solid #2a3a5c;
}

.description-content p {
  font-size: 16px;
  color: #8892b0;
  margin: 0 0 30px 0;
  line-height: 1.7;
}

.feature-list {
  margin-top: 40px;
}

.feature-list-title {
  font-size: 20px;
  font-weight: 600;
  color: #ffffff;
  margin: 0 0 20px 0;
}

.feature-list ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.feature-list li {
  font-size: 16px;
  color: #8892b0;
  margin: 0 0 12px 0;
  padding-left: 30px;
  position: relative;
  line-height: 1.5;
}

.feature-list li i {
  position: absolute;
  left: 0;
  top: 2px;
  color: #409EFF;
  font-size: 18px;
}

.related-products-section {
  padding: 80px 0;
  background: #000;
  border-top: 1px solid #2a3a5c;
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
  background: #16213e;
  border: 1px solid #2a3a5c;
  border-radius: 16px;
  overflow: hidden;
  transition: all 0.3s ease;
  height: 100%;
}

.product-card:hover {
  transform: translateY(-8px);
  box-shadow: 0 12px 40px rgba(64, 158, 255, 0.2);
  border-color: #409EFF;
}

.product-image {
  position: relative;
  width: 95%;
  height: 170px;
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
  color: #ffffff;
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
  color: #8892b0;
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
  border-radius: 8px;
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

@media (max-width: 1200px) {
  .product-detail-inner {
    flex-direction: column;
    align-items: center;
  }

  .product-image-container,
  .product-info-container {
    max-width: 100%;
    width: 100%;
  }

  .product-image-container {
    margin-bottom: 60px;
  }

  .main-image {
    max-width: 600px;
    margin: 0 auto;
  }
}

@media (max-width: 768px) {
  .breadcrumb-inner,
  .product-detail-inner,
  .product-description-inner,
  .related-products-inner {
    padding: 0 20px;
  }

  .product-detail-section,
  .product-description-section,
  .related-products-section {
    padding: 40px 0;
  }

  .product-title {
    font-size: 24px;
  }

  .product-price {
    font-size: 24px;
  }

  .specs-grid {
    grid-template-columns: 1fr;
  }

  .action-buttons {
    flex-direction: column;
  }

  .description-content {
    padding: 24px;
  }

  .related-product-item {
    width: calc(50% - 12px);
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
  .related-product-item {
    width: 100%;
  }

  .thumbnail-item {
    width: 80px;
    height: 80px;
  }
}
</style>
