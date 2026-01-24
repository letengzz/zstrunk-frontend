<template>
  <div class="about-page">
    <TopBar class="floating-topbar" />
    <div class="hero-section">
      <div class="hero-overlay"></div>
      <div class="hero-content">
        <h1 class="hero-title">About Us</h1>
        <p class="hero-subtitle">Professional Heavy Equipment Manufacturer</p>
      </div>
    </div>

    <div class="about-section">
      <div class="about-container">
        <div class="about-content">
          <h2 class="section-title">Our Story</h2>
          <p class="about-text">
            Founded with a vision to revolutionize the heavy equipment industry, we have been dedicated to manufacturing high-quality tanker trucks and construction machinery for over a decade. Our commitment to excellence, innovation, and customer satisfaction has made us a trusted partner for businesses worldwide.
          </p>
          <p class="about-text">
            We specialize in producing a wide range of tanker trucks including fuel tankers, water tankers, and chemical tankers, as well as excavators and other construction equipment. Every product is designed with precision engineering and manufactured to meet international safety and quality standards.
          </p>
        </div>
        <div class="about-image">
          <img src="/public/images/about-factory.jpg" alt="Our Factory" />
        </div>
      </div>
    </div>

    <div class="stats-section">
      <div class="stats-container">
        <div class="stat-item">
          <div class="stat-number">15+</div>
          <div class="stat-label">Years Experience</div>
        </div>
        <div class="stat-item">
          <div class="stat-number">50+</div>
          <div class="stat-label">Countries Served</div>
        </div>
        <div class="stat-item">
          <div class="stat-number">5000+</div>
          <div class="stat-label">Units Delivered</div>
        </div>
        <div class="stat-item">
          <div class="stat-number">200+</div>
          <div class="stat-label">Team Members</div>
        </div>
      </div>
    </div>

    <div class="features-section">
      <div class="features-container">
        <h2 class="section-title">Why Choose Us</h2>
        <p class="section-subtitle">What sets us apart from the competition</p>
        <div class="features-grid">
          <div class="feature-card">
            <div class="feature-icon">
              <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor">
                <path d="M12 2L2 7l10 5 10-5-10-5zM2 17l10 5 10-5M2 12l10 5 10-5"/>
              </svg>
            </div>
            <h3 class="feature-title">Quality First</h3>
            <p class="feature-desc">ISO certified manufacturing process ensuring top-notch quality in every product</p>
          </div>
          <div class="feature-card">
            <div class="feature-icon">
              <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor">
                <path d="M12 22s8-4 8-10V5l-8-3-8 3v7c0 6 8 10 8 10z"/>
              </svg>
            </div>
            <h3 class="feature-title">Safety Certified</h3>
            <p class="feature-desc">All products meet international safety standards and certifications</p>
          </div>
          <div class="feature-card">
            <div class="feature-icon">
              <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor">
                <circle cx="12" cy="12" r="10"/>
                <path d="M12 6v6l4 2"/>
              </svg>
            </div>
            <h3 class="feature-title">On-Time Delivery</h3>
            <p class="feature-desc">Efficient logistics network ensuring timely delivery worldwide</p>
          </div>
          <div class="feature-card">
            <div class="feature-icon">
              <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor">
                <path d="M21 15a2 2 0 0 1-2 2H7l-4 4V5a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2z"/>
              </svg>
            </div>
            <h3 class="feature-title">24/7 Support</h3>
            <p class="feature-desc">Dedicated customer service team available around the clock</p>
          </div>
        </div>
      </div>
    </div>

    <div class="process-section">
      <div class="process-container">
        <h2 class="section-title">Our Process</h2>
        <p class="section-subtitle">From design to delivery, excellence at every step</p>
        <div class="process-steps">
          <div class="process-step">
            <div class="step-number">01</div>
            <div class="step-content">
              <h3 class="step-title">Design & Engineering</h3>
              <p class="step-desc">Our expert engineers design products using advanced CAD software and simulation tools</p>
            </div>
          </div>
          <div class="process-step">
            <div class="step-number">02</div>
            <div class="step-content">
              <h3 class="step-title">Manufacturing</h3>
              <p class="step-desc">State-of-the-art production facility with automated assembly lines</p>
            </div>
          </div>
          <div class="process-step">
            <div class="step-number">03</div>
            <div class="step-content">
              <h3 class="step-title">Quality Control</h3>
              <p class="step-desc">Rigorous testing and inspection at every stage of production</p>
            </div>
          </div>
          <div class="process-step">
            <div class="step-number">04</div>
            <div class="step-content">
              <h3 class="step-title">Global Shipping</h3>
              <p class="step-desc">Efficient logistics partners ensuring safe delivery worldwide</p>
            </div>
          </div>
        </div>
      </div>
    </div>

    <footer class="footer">
      <div class="footer-content">
        <el-row :gutter="40">
          <el-col :xs="24" :sm="12" :md="6">
            <div class="footer-brand">
              <img src="/public/images/logo.svg" alt="Logo" class="footer-logo">
              <span class="footer-company-name">ZHI SHUN</span>
              <p class="footer-desc">Professional Services</p>
            </div>
          </el-col>
          <el-col :xs="24" :sm="12" :md="6">
            <div class="footer-links">
              <h4 class="footer-title">Quick Links</h4>
              <ul class="link-list">
                <li><a href="/">Home</a></li>
                <li><a href="/products">Products</a></li>
                <li><a href="/about">About</a></li>
                <li><a href="/contact">Contact</a></li>
              </ul>
            </div>
          </el-col>
          <el-col :xs="24" :sm="12" :md="6">
            <div class="footer-links">
              <h4 class="footer-title">Products</h4>
              <ul class="link-list">
                <li><a href="/products?category=tanker">Tanker Trucks</a></li>
                <li><a href="/products?category=excavator">Excavators</a></li>
                <li><a href="/products">View All</a></li>
              </ul>
            </div>
          </el-col>
          <el-col :xs="24" :sm="12" :md="6">
            <div class="footer-contact">
              <h4 class="footer-title">Contact Us</h4>
              <ul class="contact-list">
                <li>Email: info@zhishun.com</li>
                <li>Phone: +86 123 4567 8900</li>
                <li>Address: China</li>
              </ul>
            </div>
          </el-col>
        </el-row>
        <div class="footer-bottom">
          <p class="copyright">&copy; 2024 ZHI SHUN. All rights reserved.</p>
        </div>
      </div>
    </footer>

    <FloatingContact />
  </div>
</template>

<script setup>
import TopBar from '@/components/TopBar.vue'
import FloatingContact from '@/components/FloatingContact.vue'
</script>

<style scoped>
.about-page {
  min-height: 100vh;
  background: #0a0a0a;
}

.floating-topbar {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 1000;
}

.hero-section {
  position: relative;
  height: 500px;
  background: url('/public/images/about-hero.jpg') center/cover no-repeat;
  display: flex;
  align-items: center;
  justify-content: center;
}

.hero-overlay {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: linear-gradient(135deg, rgba(10, 10, 10, 0.9) 0%, rgba(22, 33, 62, 0.8) 100%);
}

.hero-content {
  position: relative;
  z-index: 1;
  text-align: center;
  padding: 0 20px;
}

.hero-title {
  font-size: 56px;
  font-weight: 700;
  color: #ffffff;
  margin: 0 0 20px 0;
  text-transform: uppercase;
  letter-spacing: 4px;
}

.hero-subtitle {
  font-size: 20px;
  color: #8892b0;
  margin: 0;
}

.about-section {
  padding: 100px 40px;
  background: #0a0a0a;
}

.about-container {
  max-width: 1400px;
  margin: 0 auto;
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 60px;
  align-items: center;
}

.section-title {
  font-size: 36px;
  font-weight: 700;
  color: #ffffff;
  text-align: left;
  margin: 0 0 24px 0;
}

.section-subtitle {
  font-size: 16px;
  font-weight: 400;
  color: #8892b0;
  text-align: center;
  margin: 0 0 40px 0;
}

.about-text {
  font-size: 16px;
  color: #8892b0;
  line-height: 1.8;
  margin: 0 0 20px 0;
}

.about-image {
  position: relative;
  border-radius: 16px;
  overflow: hidden;
}

.about-image img {
  width: 100%;
  height: 400px;
  object-fit: cover;
}

.stats-section {
  padding: 80px 40px;
  background: linear-gradient(180deg, #0a0a0a 0%, #16213e 100%);
}

.stats-container {
  max-width: 1400px;
  margin: 0 auto;
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 40px;
}

.stat-item {
  text-align: center;
}

.stat-number {
  font-size: 48px;
  font-weight: 700;
  color: #409EFF;
  margin-bottom: 10px;
}

.stat-label {
  font-size: 16px;
  color: #8892b0;
}

.features-section {
  padding: 100px 40px;
  background: #0a0a0a;
}

.features-container {
  max-width: 1400px;
  margin: 0 auto;
}

.features-grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 30px;
}

.feature-card {
  background: #16213e;
  border: 1px solid #2a3a5c;
  border-radius: 16px;
  padding: 30px;
  text-align: center;
  transition: all 0.3s ease;
}

.feature-card:hover {
  transform: translateY(-8px);
  border-color: #409EFF;
  box-shadow: 0 12px 40px rgba(64, 158, 255, 0.2);
}

.feature-icon {
  width: 60px;
  height: 60px;
  margin: 0 auto 20px;
  color: #409EFF;
}

.feature-icon svg {
  width: 100%;
  height: 100%;
}

.feature-title {
  font-size: 20px;
  font-weight: 600;
  color: #ffffff;
  margin: 0 0 12px 0;
}

.feature-desc {
  font-size: 14px;
  color: #8892b0;
  line-height: 1.6;
  margin: 0;
}

.process-section {
  padding: 100px 40px;
  background: linear-gradient(180deg, #0a0a0a 0%, #16213e 100%);
}

.process-container {
  max-width: 1400px;
  margin: 0 auto;
}

.process-steps {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 30px;
  margin-top: 60px;
}

.process-step {
  position: relative;
  padding: 30px;
  background: #16213e;
  border: 1px solid #2a3a5c;
  border-radius: 16px;
  transition: all 0.3s ease;
}

.process-step:hover {
  transform: translateY(-8px);
  border-color: #409EFF;
}

.step-number {
  font-size: 48px;
  font-weight: 700;
  color: rgba(64, 158, 255, 0.2);
  margin-bottom: 20px;
}

.step-title {
  font-size: 18px;
  font-weight: 600;
  color: #ffffff;
  margin: 0 0 12px 0;
}

.step-desc {
  font-size: 14px;
  color: #8892b0;
  line-height: 1.6;
  margin: 0;
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
  height: 2px;
  background: #409EFF;
}

.footer-links {
  margin-bottom: 30px;
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
  transition: color 0.3s ease;
}

.link-list a:hover {
  color: #409EFF;
}

.contact-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.contact-list li {
  color: #8892b0;
  font-size: 14px;
  margin-bottom: 12px;
}

.footer-bottom {
  margin-top: 50px;
  padding-top: 20px;
  border-top: 1px solid #2a3a5c;
  text-align: center;
}

.copyright {
  font-size: 14px;
  color: #8892b0;
  margin: 0;
}

@media (max-width: 1200px) {
  .features-grid,
  .process-steps,
  .stats-container {
    grid-template-columns: repeat(2, 1fr);
  }
}

@media (max-width: 992px) {
  .about-container {
    grid-template-columns: 1fr;
    gap: 40px;
  }

  .about-image {
    order: -1;
  }
}

@media (max-width: 768px) {
  .hero-title {
    font-size: 36px;
  }

  .hero-subtitle {
    font-size: 16px;
  }

  .section-title {
    font-size: 28px;
    text-align: center;
  }

  .features-grid,
  .process-steps,
  .stats-container {
    grid-template-columns: 1fr;
  }
}

@media (max-width: 480px) {
  .about-section,
  .features-section,
  .process-section,
  .stats-section {
    padding: 60px 20px;
  }

  .hero-section {
    height: 400px;
  }
}
</style>
