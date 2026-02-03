<template>
  <footer class="footer">
    <div class="footer-content">
      <el-row :gutter="20">
        <el-col :xs="24" :sm="12" :md="4">
          <div class="footer-brand">
            <img src="/public/images/logo.png" alt="Logo" class="footer-logo">
            <span class="footer-company-name">ZHI SHUN</span>
            <p class="footer-desc">{{ description }}</p>
          </div>
        </el-col>
        <el-col :xs="24" :sm="12" :md="5">
          <div class="footer-links">
            <h4 class="footer-title">{{ quickLinksTitle }}</h4>
            <ul class="link-list">
              <li v-for="(link, index) in quickLinks" :key="index">
                <a :href="link.url">{{ link.text }}</a>
              </li>
            </ul>
          </div>
        </el-col>
        <el-col :xs="24" :sm="12" :md="8">
          <div class="footer-links">
            <h4 class="footer-title">{{ productsTitle }}</h4>
            <div class="products-grid">
              <div class="product-column">
                <div v-for="(link, index) in productLinksLeft" :key="'left-'+index">
                  <a :href="link.url" class="product-category-link">{{ link.text }}</a>
                </div>
              </div>
              <div class="product-column">
                <div v-for="(link, index) in productLinksRight" :key="'right-'+index">
                  <a :href="link.url" class="product-category-link">{{ link.text }}</a>
                </div>
              </div>
            </div>
          </div>
        </el-col>
        <el-col :xs="24" :sm="12" :md="7">
          <div class="footer-links footer-contact">
            <h4 class="footer-title">{{ contactTitle }}</h4>
            <div v-if="showContactIcons" class="contact-info">
              <div v-if="phone" class="contact-row">
                <div class="contact-icon i-ep-phone"></div>
                <span>{{ phone }}</span>
              </div>
              <div v-if="email" class="contact-row">
                <div class="contact-icon i-ep-message"></div>
                <span>{{ email }}</span>
              </div>
              <div v-if="whatsapp" class="contact-row">
                <div class="contact-icon i-ant-design-whats-app-outlined"></div>
                <span>{{ whatsapp }}</span>
              </div>
              <div v-if="address" class="contact-row">
                <div class="contact-icon i-ep-location"></div>
                <span>{{ address }}</span>
              </div>
            </div>
            <ul v-else class="contact-list">
              <li v-for="(item, index) in contactInfo" :key="index">{{ item }}</li>
            </ul>
          </div>
        </el-col>
      </el-row>
      <div class="footer-bottom">
        <p class="copyright">&copy; {{ currentYear }} ZHI SHUN. All rights reserved.</p>
      </div>
    </div>
  </footer>
</template>

<script setup lang="ts">
interface LinkItem {
  text: string
  url: string
}

withDefaults(defineProps<{
  description?: string
  quickLinksTitle?: string
  quickLinks?: LinkItem[]
  productsTitle?: string
  productLinksLeft?: LinkItem[]
  productLinksRight?: LinkItem[]
  contactTitle?: string
  showContactIcons?: boolean
  phone?: string
  email?: string
  whatsapp?: string
  address?: string
  contactInfo?: string[]
}>(), {
  description: 'Professional Services',
  quickLinksTitle: 'Quick Links',
  quickLinks: () => [
    { text: 'Home', url: '/' },
    { text: 'About Us', url: '/about' }
  ],
  productsTitle: 'Products',
  productLinksLeft: () => [
    { text: 'Liquid and Power Transport Trailers', url: '/products?category=LiquidandPowerTransportTrailers' },
    { text: 'Container Semi Trailer', url: '/products?category=ContainerSemiTrailer' },
    { text: 'Semi Trailer', url: '/products?category=SemiTrailer' },
    { text: 'Shacman Trucks', url: '/products?category=ShacmanTrucks' }
  ],
  productLinksRight: () => [
    { text: 'Accessories', url: '/products?category=Accessories' },
    { text: 'Sinotruck Howo', url: '/products?category=SinotruckHowo' },
    { text: 'Existing Trucks and Trailers', url: '/products?category=ExistingTrucksAndTrailers' },
    { text: 'Excavators', url: '/products?category=excavator' }
  ],
  contactTitle: 'Contact Us',
  showContactIcons: true,
  contactInfo: () => [
    'Beijing, China',
    'Phone: +86-15588751133',
    'Email: service@example.com',
    'WhatsApp: +86-15588751133'
  ]
})

const currentYear = new Date().getFullYear()
</script>

<style scoped>
.footer {
  background: #F6F5ED;
  padding: 60px 40px 30px;
  border-top: 1px solid #e2e8f0;
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
  color: #1a2a4a;
  display: block;
  margin-bottom: 15px;
  letter-spacing: 1px;
}

.footer-desc {
  font-size: 14px;
  color: #5a6a7a;
  margin: 0;
  line-height: 1.7;
}

.footer-title {
  font-size: 18px;
  font-weight: 600;
  color: #1a2a4a;
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
  background: linear-gradient(90deg, #8B0000, #FF0000);
  border-radius: 2px;
}

.footer-links {
  margin-bottom: 30px;
}

.products-grid {
  display: flex;
  gap: 20px;
}

.product-column {
  flex: 1;
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.product-category-link {
  color: #5a6a7a;
  text-decoration: none;
  font-size: 14px;
  transition: all 0.3s ease;
  display: inline-block;
  position: relative;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.product-category-link::before {
  content: '›';
  margin-right: 8px;
  color: #FF0000;
  opacity: 0;
  transform: translateX(-10px);
  transition: all 0.3s ease;
  display: inline-block;
}

.product-category-link:hover {
  color: #FF0000;
  transform: translateX(5px);
}

.product-category-link:hover::before {
  opacity: 1;
  transform: translateX(0);
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
  color: #5a6a7a;
  text-decoration: none;
  font-size: 14px;
  transition: all 0.3s ease;
  display: inline-block;
  position: relative;
}

.link-list a::before {
  content: '›';
  margin-right: 8px;
  color: #FF0000;
  opacity: 0;
  transform: translateX(-10px);
  transition: all 0.3s ease;
  display: inline-block;
}

.link-list a:hover {
  color: #FF0000;
  transform: translateX(5px);
}

.link-list a:hover::before {
  opacity: 1;
  transform: translateX(0);
}

.footer-contact {
  padding-left: 0;
}

.contact-info {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.contact-row {
  display: flex;
  align-items: center;
  gap: 12px;
  color: #5a6a7a;
  font-size: 14px;
}

.contact-icon {
  font-size: 18px;
  width: 28px;
  text-align: center;
}

.contact-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.contact-list li {
  color: #5a6a7a;
  font-size: 14px;
  margin-bottom: 12px;
}

.footer-bottom {
  margin-top: 50px;
  padding-top: 25px;
  border-top: 1px solid #e0ddd5;
  text-align: center;
}

.copyright {
  font-size: 14px;
  color: #7a8a9a;
  margin: 0;
}

@media (max-width: 768px) {
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
</style>
