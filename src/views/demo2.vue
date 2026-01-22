<template>
  <div class="tank-truck-website">
    <!-- 导航栏 -->
    <header class="navbar">
      <div class="container">
        <div class="navbar-brand">
          <img src="/images/logo.svg" alt="罐车专家" class="logo">
          <h1 class="brand-name">罐车专家</h1>
        </div>
        <nav class="navbar-nav">
          <a href="#" class="nav-link active">首页 <i class="nav-arrow">▼</i></a>
          <a href="#services" class="nav-link">服务 <i class="nav-arrow">▼</i></a>
          <a href="#products" class="nav-link">产品 <i class="nav-arrow">▼</i></a>
          <a href="#about" class="nav-link">关于我们 <i class="nav-arrow">▼</i></a>
          <a href="#contact" class="nav-link">联系我们</a>
        </nav>
        <div class="navbar-actions">
          <div class="phone-number"><i class="phone-icon">☎</i> (086) 123-4567 890</div>
          <button class="btn-primary">预约服务 <i class="btn-arrow">▶</i></button>
        </div>
      </div>
    </header>

    <!-- 英雄区域 -->
    <section class="hero">
      <div class="hero-content">
        <h2 class="hero-title">PREMIER<br>TANK TRUCK</h2>
        <p class="hero-subtitle">罐车专家是一个真正改变人们对罐车服务认知的品牌。</p>
        <button class="btn-get-started">GET STARTED</button>
      </div>
    </section>

    <!-- 优惠服务卡片 -->
    <section class="services-cards">
      <div class="container">
        <div class="cards-grid">
          <div class="service-card">
            <div class="card-header">
              <span class="badge">INSTANT SAVINGS</span>
              <h3>优质罐车轮胎</h3>
              <p class="price">¥999.00</p>
            </div>
            <div class="card-image">
              <img src="https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=heavy%20duty%20truck%20tires%2C%20professional%20photography%2C%20high%20quality%2C%20orange%20accents&image_size=square" alt="罐车轮胎">
            </div>
          </div>
          <div class="service-card">
            <div class="card-header">
              <span class="badge">COMPLETE</span>
              <h3>罐车保养套餐</h3>
              <p class="price">¥299.00</p>
            </div>
            <div class="card-image">
              <img src="https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=mechanic%20working%20on%20tank%20truck%2C%20professional%20photography%2C%20high%20quality%2C%20orange%20accents&image_size=square" alt="罐车保养">
            </div>
          </div>
          <div class="service-card">
            <div class="card-header">
              <span class="badge">IT'S TIME AGAIN</span>
              <h3>标准油罐清洗</h3>
              <p class="price">25% OFF</p>
            </div>
            <div class="card-image">
              <img src="https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=tank%20truck%20cleaning%2C%20professional%20photography%2C%20high%20quality%2C%20orange%20accents&image_size=square" alt="油罐清洗">
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- 品牌合作伙伴 -->
    <section class="partners">
      <div class="container">
        <div class="partners-grid">
          <div class="partner-logo">
            <i class="el-icon-truck"></i>
            <span>东风</span>
          </div>
          <div class="partner-logo">
            <i class="el-icon-truck"></i>
            <span>解放</span>
          </div>
          <div class="partner-logo">
            <i class="el-icon-truck"></i>
            <span>重汽</span>
          </div>
          <div class="partner-logo">
            <i class="el-icon-truck"></i>
            <span>福田</span>
          </div>
          <div class="partner-logo">
            <i class="el-icon-truck"></i>
            <span>陕汽</span>
          </div>
          <div class="partner-logo">
            <i class="el-icon-truck"></i>
            <span>红岩</span>
          </div>
          <div class="partner-logo">
            <i class="el-icon-truck"></i>
            <span>江淮</span>
          </div>
        </div>
      </div>
    </section>

    <!-- 服务流程 -->
    <section class="service-process">
      <div class="container">
        <h2 class="section-title">Quality Servicing Opportunity</h2>
        <div class="process-grid">
          <div class="process-item">
            <div class="process-image">
              <img src="https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=mechanic%20inspecting%20tank%20truck%20engine%2C%20professional%20photography%2C%20high%20quality%2C%20orange%20accents&image_size=square" alt="罐车检查">
              <div class="process-number">01</div>
            </div>
            <div class="process-content">
              <h3>罐车检查套餐</h3>
              <button class="btn-secondary">BOOK NOW »</button>
            </div>
          </div>
          <div class="process-item">
            <div class="process-image">
              <img src="https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=tank%20truck%20washing%2C%20professional%20photography%2C%20high%20quality%2C%20orange%20accents&image_size=square" alt="罐车清洗">
              <div class="process-number">02</div>
            </div>
            <div class="process-content">
              <h3>罐车外部清洗与抛光</h3>
              <button class="btn-secondary">BOOK NOW »</button>
            </div>
          </div>
          <div class="process-item">
            <div class="process-image">
              <img src="https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=tank%20truck%20repair%2C%20professional%20photography%2C%20high%20quality%2C%20orange%20accents&image_size=square" alt="罐车维修">
              <div class="process-number">03</div>
            </div>
            <div class="process-content">
              <h3>罐车划痕修复</h3>
              <button class="btn-secondary">BOOK NOW »</button>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- 产品展示 -->
    <section class="products">
      <div class="container">
        <div class="section-header">
          <span class="section-badge">POPULAR PRODUCTS</span>
          <h2>We're Proud Of Recent Same Success Work</h2>
        </div>
        <div class="products-grid">
          <div class="product-item">
            <div class="product-image">
              <img src="https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=tank%20truck%20spare%20parts%2C%20professional%20photography%2C%20high%20quality%2C%20orange%20accents&image_size=square" alt="罐车配件">
            </div>
            <div class="product-content">
              <div class="product-rating">
                <i class="el-icon-star"></i>
                <i class="el-icon-star"></i>
                <i class="el-icon-star"></i>
                <i class="el-icon-star"></i>
                <i class="el-icon-star"></i>
              </div>
              <h3>罐车配件套装</h3>
              <p class="product-price">¥1599.00</p>
            </div>
          </div>
          <div class="product-item">
            <div class="product-image">
              <img src="https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=tank%20truck%20suspension%20parts%2C%20professional%20photography%2C%20high%20quality%2C%20orange%20accents&image_size=square" alt="罐车悬挂系统">
            </div>
            <div class="product-content">
              <div class="product-rating">
                <i class="el-icon-star"></i>
                <i class="el-icon-star"></i>
                <i class="el-icon-star"></i>
                <i class="el-icon-star"></i>
                <i class="el-icon-star"></i>
              </div>
              <h3>罐车悬挂系统</h3>
              <p class="product-price">¥523.35</p>
            </div>
          </div>
          <div class="product-item">
            <div class="product-image">
              <img src="https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=tank%20truck%20wheels%2C%20professional%20photography%2C%20high%20quality%2C%20orange%20accents&image_size=square" alt="罐车车轮">
            </div>
            <div class="product-content">
              <div class="product-rating">
                <i class="el-icon-star"></i>
                <i class="el-icon-star"></i>
                <i class="el-icon-star"></i>
                <i class="el-icon-star"></i>
                <i class="el-icon-star"></i>
              </div>
              <h3>罐车铝合金轮毂</h3>
              <p class="product-price">¥914.66</p>
            </div>
          </div>
          <div class="product-item">
            <div class="product-image">
              <img src="https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=tank%20truck%20shock%20absorbers%2C%20professional%20photography%2C%20high%20quality%2C%20orange%20accents&image_size=square" alt="罐车减震器">
            </div>
            <div class="product-content">
              <div class="product-rating">
                <i class="el-icon-star"></i>
                <i class="el-icon-star"></i>
                <i class="el-icon-star"></i>
                <i class="el-icon-star"></i>
                <i class="el-icon-star"></i>
              </div>
              <h3>罐车减震器</h3>
              <p class="product-price">¥873.33 - ¥918.33</p>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- 联系我们 -->
    <section id="contact" class="contact">
      <div class="container">
        <h2 class="section-title">联系我们</h2>
        <p class="section-subtitle">如果您对我们的产品和服务感兴趣，欢迎随时联系我们</p>
        <div class="contact-content">
          <div class="contact-info">
            <div class="contact-item">
              <i class="el-icon-map-location"></i>
              <div>
                <h3>公司地址</h3>
                <p>中国江苏省徐州市经济技术开发区</p>
              </div>
            </div>
            <div class="contact-item">
              <i class="el-icon-phone"></i>
              <div>
                <h3>联系电话</h3>
                <p>400-123-4567</p>
              </div>
            </div>
            <div class="contact-item">
              <i class="el-icon-message"></i>
              <div>
                <h3>电子邮箱</h3>
                <p>info@tanktruck.com</p>
              </div>
            </div>
          </div>
          <div class="contact-form">
            <form>
              <div class="form-group">
                <label for="name">姓名</label>
                <input type="text" id="name" class="form-control" placeholder="请输入您的姓名">
              </div>
              <div class="form-group">
                <label for="phone">电话</label>
                <input type="tel" id="phone" class="form-control" placeholder="请输入您的电话">
              </div>
              <div class="form-group">
                <label for="email">邮箱</label>
                <input type="email" id="email" class="form-control" placeholder="请输入您的邮箱">
              </div>
              <div class="form-group">
                <label for="message">留言</label>
                <textarea id="message" class="form-control" rows="4" placeholder="请输入您的留言"></textarea>
              </div>
              <button type="submit" class="btn-primary">提交</button>
            </form>
          </div>
        </div>
      </div>
    </section>

    <!-- 页脚 -->
    <footer class="footer">
      <div class="container">
        <div class="footer-content">
          <div class="footer-brand">
            <img src="/images/logo.svg" alt="罐车专家" class="logo">
            <h3 class="brand-name">罐车专家</h3>
            <p class="brand-description">专业罐车解决方案提供商</p>
          </div>
          <div class="footer-links">
            <h4>快速链接</h4>
            <ul>
              <li><a href="#">首页</a></li>
              <li><a href="#services">服务</a></li>
              <li><a href="#products">产品</a></li>
              <li><a href="#about">关于我们</a></li>
              <li><a href="#contact">联系我们</a></li>
            </ul>
          </div>
          <div class="footer-contact">
            <h4>联系方式</h4>
            <ul>
              <li><i class="el-icon-map-location"></i> 中国江苏省徐州市经济技术开发区</li>
              <li><i class="el-icon-phone"></i> 400-123-4567</li>
              <li><i class="el-icon-message"></i> info@tanktruck.com</li>
            </ul>
          </div>
        </div>
        <div class="footer-bottom">
          <p>&copy; 2024 罐车专家. 保留所有权利.</p>
        </div>
      </div>
    </footer>
  </div>
</template>

<script setup lang="ts">

</script>

<style scoped>
/* 全局样式重置 */
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: 'Arial', sans-serif;
  line-height: 1.6;
  color: #333;
}

/* 容器样式 */
.container {
  width: 100%;
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 20px;
}

/* 导航栏样式 */
.navbar {
  background-color: #000;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.3);
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 1000;
}

.navbar .container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px 20px;
}

.navbar-brand {
  display: flex;
  align-items: center;
}

.logo {
  width: 50px;
  height: 50px;
  margin-right: 10px;
  filter: brightness(0) invert(1);
}

.brand-name {
  font-size: 24px;
  font-weight: bold;
  color: #fff;
}

.navbar-nav {
  display: flex;
  gap: 20px;
}

.nav-link {
  text-decoration: none;
  color: #fff;
  font-weight: 500;
  transition: color 0.3s;
  display: flex;
  align-items: center;
  gap: 5px;
}

.nav-link:hover,
.nav-link.active {
  color: #ff6b00;
}

.nav-arrow {
  font-size: 12px;
}

.navbar-actions {
  display: flex;
  align-items: center;
  gap: 20px;
}

.phone-number {
  font-weight: 500;
  color: #fff;
  display: flex;
  align-items: center;
  gap: 5px;
  background-color: rgba(255, 255, 255, 0.1);
  padding: 8px 12px;
  border-radius: 4px;
}

.phone-icon {
  font-size: 14px;
}

/* 按钮样式 */
.btn-primary {
  background-color: #ff6b00;
  color: #fff;
  border: none;
  padding: 10px 20px;
  border-radius: 4px;
  cursor: pointer;
  font-weight: 500;
  transition: background-color 0.3s;
  display: flex;
  align-items: center;
  gap: 5px;
}

.btn-primary:hover {
  background-color: #e55a00;
}

.btn-arrow {
  font-size: 14px;
  font-weight: bold;
}

.btn-secondary {
  background-color: #fff;
  color: #ff6b00;
  border: 1px solid #ff6b00;
  padding: 10px 20px;
  border-radius: 4px;
  cursor: pointer;
  font-weight: 500;
  transition: all 0.3s;
}

.btn-secondary:hover {
  background-color: #ff6b00;
  color: #fff;
}

/* 英雄区域样式 */
.hero {
  background-image: linear-gradient(rgba(0, 0, 0, 0.7), rgba(0, 0, 0, 0.7)), url('https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=tank%20truck%20service%20garage%2C%20professional%20photography%2C%20high%20quality%2C%20dim%20lighting%2C%20orange%20accents&image_size=landscape_16_9');
  background-size: cover;
  background-position: center;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #fff;
  text-align: center;
  margin-top: 80px;
}

.hero-content {
  max-width: 800px;
  padding: 0 20px;
}

.hero-title {
  font-size: 72px;
  font-weight: bold;
  margin-bottom: 20px;
  line-height: 1.2;
}

.hero-title span {
  display: block;
}

.hero-subtitle {
  font-size: 18px;
  color: #ccc;
  margin-bottom: 40px;
}

.btn-get-started {
  background-color: #ff6b00;
  color: #fff;
  border: none;
  padding: 15px 30px;
  border-radius: 50px;
  cursor: pointer;
  font-weight: 500;
  font-size: 16px;
  transition: background-color 0.3s;
}

.btn-get-started:hover {
  background-color: #e55a00;
}

/* 服务卡片样式 */
.services-cards {
  background-color: #f8f9fa;
  padding: 80px 0;
}

.cards-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 30px;
}

.service-card {
  background-color: #fff;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s, box-shadow 0.3s;
}

.service-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
}

.card-header {
  padding: 20px;
}

.badge {
  background-color: #ff6b00;
  color: #fff;
  padding: 5px 10px;
  border-radius: 4px;
  font-size: 12px;
  font-weight: 500;
  margin-bottom: 10px;
  display: inline-block;
}

.card-header h3 {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 10px;
}

.price {
  font-size: 24px;
  font-weight: bold;
  color: #ff6b00;
}

.card-image {
  height: 200px;
  overflow: hidden;
}

.card-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

/* 合作伙伴样式 */
.partners {
  background-color: #000;
  color: #fff;
  padding: 60px 0;
}

.partners-grid {
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-wrap: wrap;
  gap: 40px;
}

.partner-logo {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 10px;
}

.partner-logo i {
  font-size: 48px;
  color: #ff6b00;
}

.partner-logo span {
  font-size: 18px;
  font-weight: 500;
}

/* 服务流程样式 */
.service-process {
  padding: 80px 0;
  background-color: #f8f9fa;
}

.section-title {
  font-size: 36px;
  font-weight: bold;
  text-align: center;
  margin-bottom: 60px;
  color: #333;
}

.process-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 30px;
}

.process-item {
  background-color: #fff;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s, box-shadow 0.3s;
}

.process-item:hover {
  transform: translateY(-5px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
}

.process-image {
  position: relative;
  height: 250px;
  overflow: hidden;
}

.process-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.process-number {
  position: absolute;
  top: 20px;
  right: 20px;
  background-color: #ff6b00;
  color: #fff;
  width: 40px;
  height: 40px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-weight: bold;
  font-size: 16px;
}

.process-content {
  padding: 20px;
}

.process-content h3 {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 20px;
  color: #333;
}

.process-content .btn-secondary {
  width: 100%;
}

/* 产品展示样式 */
.products {
  padding: 80px 0;
}

.section-header {
  text-align: center;
  margin-bottom: 60px;
}

.section-badge {
  background-color: #ff6b00;
  color: #fff;
  padding: 5px 15px;
  border-radius: 4px;
  font-size: 14px;
  font-weight: 500;
  margin-bottom: 20px;
  display: inline-block;
}

.section-header h2 {
  font-size: 36px;
  font-weight: bold;
  color: #333;
}

.products-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 30px;
}

.product-item {
  background-color: #fff;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s, box-shadow 0.3s;
}

.product-item:hover {
  transform: translateY(-5px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
}

.product-image {
  height: 200px;
  overflow: hidden;
}

.product-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.product-content {
  padding: 20px;
  text-align: center;
}

.product-rating {
  color: #ffc107;
  margin-bottom: 10px;
}

.product-content h3 {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 10px;
  color: #333;
}

.product-price {
  font-size: 16px;
  font-weight: bold;
  color: #ff6b00;
}

/* 联系我们样式 */
.contact {
  padding: 80px 0;
  background-color: #f8f9fa;
}

.section-subtitle {
  font-size: 16px;
  color: #666;
  text-align: center;
  margin-bottom: 60px;
}

.contact-content {
  display: flex;
  gap: 40px;
}

.contact-info {
  flex: 1;
}

.contact-item {
  display: flex;
  gap: 20px;
  margin-bottom: 30px;
}

.contact-item i {
  font-size: 24px;
  color: #ff6b00;
  flex-shrink: 0;
}

.contact-item h3 {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 5px;
  color: #333;
}

.contact-item p {
  font-size: 14px;
  color: #666;
}

.contact-form {
  flex: 1;
  background-color: #fff;
  padding: 40px;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.form-group {
  margin-bottom: 20px;
}

.form-group label {
  display: block;
  font-weight: 500;
  margin-bottom: 10px;
  color: #333;
}

.form-control {
  width: 100%;
  padding: 12px;
  border: 1px solid #ced4da;
  border-radius: 4px;
  font-size: 14px;
  transition: border-color 0.3s;
}

.form-control:focus {
  outline: none;
  border-color: #ff6b00;
  box-shadow: 0 0 0 0.2rem rgba(255, 107, 0, 0.25);
}

.contact-form .btn-primary {
  width: 100%;
  padding: 12px;
  font-size: 16px;
}

/* 页脚样式 */
.footer {
  background-color: #333;
  color: #fff;
  padding: 60px 0 30px;
}

.footer-content {
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  gap: 40px;
  margin-bottom: 40px;
}

.footer-brand {
  flex: 1;
  min-width: 250px;
}

.footer-brand .logo {
  filter: brightness(0) invert(1);
}

.footer-brand .brand-name {
  color: #fff;
  margin: 10px 0;
}

.brand-description {
  font-size: 14px;
  color: #ccc;
}

.footer-links {
  flex: 1;
  min-width: 200px;
}

.footer-links h4 {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 20px;
  color: #fff;
}

.footer-links ul {
  list-style: none;
}

.footer-links li {
  margin-bottom: 10px;
}

.footer-links a {
  color: #ccc;
  text-decoration: none;
  transition: color 0.3s;
}

.footer-links a:hover {
  color: #ff6b00;
}

.footer-contact {
  flex: 1;
  min-width: 250px;
}

.footer-contact h4 {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 20px;
  color: #fff;
}

.footer-contact ul {
  list-style: none;
}

.footer-contact li {
  margin-bottom: 10px;
  display: flex;
  align-items: flex-start;
  gap: 10px;
  color: #ccc;
}

.footer-bottom {
  border-top: 1px solid #444;
  padding-top: 30px;
  text-align: center;
  font-size: 14px;
  color: #ccc;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .navbar-nav {
    display: none;
  }
  
  .hero-title {
    font-size: 48px;
  }
  
  .contact-content {
    flex-direction: column;
  }
  
  .footer-content {
    flex-direction: column;
    align-items: center;
    text-align: center;
  }
  
  .partners-grid {
    justify-content: center;
  }
}

@media (max-width: 480px) {
  .hero-title {
    font-size: 36px;
  }
  
  .section-title {
    font-size: 28px;
  }
  
  .section-header h2 {
    font-size: 28px;
  }
}
</style>