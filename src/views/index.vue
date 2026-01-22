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
          <a href="#" class="nav-link active">首页</a>
          <a href="#products" class="nav-link">产品中心</a>
          <a href="#services" class="nav-link">服务项目</a>
          <a href="#about" class="nav-link">关于我们</a>
          <a href="#contact" class="nav-link">联系我们</a>
        </nav>
        <div class="navbar-actions">
          <button class="btn-primary">获取报价</button>
        </div>
      </div>
    </header>

    <!-- 英雄区域 -->
    <section class="hero">
      <div class="container">
        <div class="hero-content">
          <h2 class="hero-title">专业罐车解决方案</h2>
          <p class="hero-subtitle">为您提供高品质、安全可靠的罐车产品和服务</p>
          <div class="hero-buttons">
            <button class="btn-primary">了解产品</button>
            <button class="btn-secondary">联系我们</button>
          </div>
        </div>
        <div class="hero-image">
          <img src="https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=modern%20tank%20truck%20on%20highway%2C%20professional%20photography%2C%20high%20quality%2C%20realistic%20details&image_size=landscape_16_9" alt="罐车展示">
        </div>
      </div>
    </section>

    <!-- 产品中心 -->
    <section id="products" class="products">
      <div class="container">
        <h2 class="section-title">产品中心</h2>
        <p class="section-subtitle">我们提供多种类型的罐车，满足不同行业的需求</p>
        <div class="products-grid">
          <div class="product-card">
            <div class="product-image">
              <img src="https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=chemical%20tank%20truck%2C%20professional%20photography%2C%20high%20quality&image_size=square" alt="化工罐车">
            </div>
            <h3 class="product-title">化工罐车</h3>
            <p class="product-description">专为运输化学液体设计，具有耐腐蚀、安全可靠的特点</p>
            <button class="btn-secondary">查看详情</button>
          </div>
          <div class="product-card">
            <div class="product-image">
              <img src="https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=oil%20tank%20truck%2C%20professional%20photography%2C%20high%20quality&image_size=square" alt="油罐车">
            </div>
            <h3 class="product-title">油罐车</h3>
            <p class="product-description">用于运输各类油品，具备防火、防爆等安全措施</p>
            <button class="btn-secondary">查看详情</button>
          </div>
          <div class="product-card">
            <div class="product-image">
              <img src="https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=food%20grade%20tank%20truck%2C%20professional%20photography%2C%20high%20quality&image_size=square" alt="食品罐车">
            </div>
            <h3 class="product-title">食品罐车</h3>
            <p class="product-description">符合食品卫生标准，用于运输食品级液体</p>
            <button class="btn-secondary">查看详情</button>
          </div>
          <div class="product-card">
            <div class="product-image">
              <img src="https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=water%20tank%20truck%2C%20professional%20photography%2C%20high%20quality&image_size=square" alt="水罐车">
            </div>
            <h3 class="product-title">水罐车</h3>
            <p class="product-description">用于运输饮用水或工业用水，具备防污染设计</p>
            <button class="btn-secondary">查看详情</button>
          </div>
        </div>
      </div>
    </section>

    <!-- 服务项目 -->
    <section id="services" class="services">
      <div class="container">
        <h2 class="section-title">服务项目</h2>
        <p class="section-subtitle">我们提供全方位的罐车相关服务</p>
        <div class="services-grid">
          <div class="service-card">
            <div class="service-icon">
              <i class="el-icon-s-tools"></i>
            </div>
            <h3 class="service-title">罐车定制</h3>
            <p class="service-description">根据客户需求定制不同规格和功能的罐车</p>
          </div>
          <div class="service-card">
            <div class="service-icon">
              <i class="el-icon-s-flag"></i>
            </div>
            <h3 class="service-title">罐车维修</h3>
            <p class="service-description">专业的罐车维修服务，确保车辆安全运行</p>
          </div>
          <div class="service-card">
            <div class="service-icon">
              <i class="el-icon-s-check"></i>
            </div>
            <h3 class="service-title">罐车检测</h3>
            <p class="service-description">定期检测罐车各项性能指标，保证安全</p>
          </div>
          <div class="service-card">
            <div class="service-icon">
              <i class="el-icon-s-marketing"></i>
            </div>
            <h3 class="service-title">罐车租赁</h3>
            <p class="service-description">提供短期或长期罐车租赁服务，灵活满足需求</p>
          </div>
        </div>
      </div>
    </section>

    <!-- 公司优势 -->
    <section class="advantages">
      <div class="container">
        <h2 class="section-title">公司优势</h2>
        <p class="section-subtitle">选择我们的罐车产品和服务，您将获得以下优势</p>
        <div class="advantages-grid">
          <div class="advantage-item">
            <div class="advantage-icon">
              <i class="el-icon-s-flag"></i>
            </div>
            <h3 class="advantage-title">专业制造</h3>
            <p class="advantage-description">拥有多年罐车制造经验，技术精湛，品质保证</p>
          </div>
          <div class="advantage-item">
            <div class="advantage-icon">
              <i class="el-icon-s-claim"></i>
            </div>
            <h3 class="advantage-title">安全可靠</h3>
            <p class="advantage-description">严格的质量控制体系，确保每一辆罐车都符合安全标准</p>
          </div>
          <div class="advantage-item">
            <div class="advantage-icon">
              <i class="el-icon-s-finance"></i>
            </div>
            <h3 class="advantage-title">性价比高</h3>
            <p class="advantage-description">合理的价格，优质的产品和服务，为客户创造最大价值</p>
          </div>
          <div class="advantage-item">
            <div class="advantage-icon">
              <i class="el-icon-s-help"></i>
            </div>
            <h3 class="advantage-title">完善服务</h3>
            <p class="advantage-description">从咨询到售后，提供全方位的贴心服务</p>
          </div>
        </div>
      </div>
    </section>

    <!-- 客户案例 -->
    <section class="cases">
      <div class="container">
        <h2 class="section-title">客户案例</h2>
        <p class="section-subtitle">我们为众多企业提供了优质的罐车产品和服务</p>
        <div class="cases-carousel">
          <div class="case-item">
            <img src="https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=tank%20truck%20fleet%20in%20industrial%20area%2C%20professional%20photography%2C%20high%20quality&image_size=landscape_16_9" alt="案例1">
            <div class="case-info">
              <h3>某化工企业</h3>
              <p>为其定制了20辆化工罐车，满足其运输需求</p>
            </div>
          </div>
          <div class="case-item">
            <img src="https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=oil%20tank%20truck%20at%20gas%20station%2C%20professional%20photography%2C%20high%20quality&image_size=landscape_16_9" alt="案例2">
            <div class="case-info">
              <h3>某石油公司</h3>
              <p>提供了15辆油罐车，确保油品安全运输</p>
            </div>
          </div>
          <div class="case-item">
            <img src="https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=food%20tank%20truck%20at%20factory%2C%20professional%20photography%2C%20high%20quality&image_size=landscape_16_9" alt="案例3">
            <div class="case-info">
              <h3>某食品企业</h3>
              <p>定制了10辆食品级罐车，保证食品运输安全</p>
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
              <li><a href="#products">产品中心</a></li>
              <li><a href="#services">服务项目</a></li>
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
  background-color: #fff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
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
}

.brand-name {
  font-size: 24px;
  font-weight: bold;
  color: #333;
}

.navbar-nav {
  display: flex;
  gap: 20px;
}

.nav-link {
  text-decoration: none;
  color: #333;
  font-weight: 500;
  transition: color 0.3s;
}

.nav-link:hover,
.nav-link.active {
  color: #007bff;
}

.navbar-actions {
  display: flex;
  gap: 10px;
}

/* 按钮样式 */
.btn-primary {
  background-color: #007bff;
  color: #fff;
  border: none;
  padding: 10px 20px;
  border-radius: 4px;
  cursor: pointer;
  font-weight: 500;
  transition: background-color 0.3s;
}

.btn-primary:hover {
  background-color: #0069d9;
}

.btn-secondary {
  background-color: #fff;
  color: #007bff;
  border: 1px solid #007bff;
  padding: 10px 20px;
  border-radius: 4px;
  cursor: pointer;
  font-weight: 500;
  transition: all 0.3s;
}

.btn-secondary:hover {
  background-color: #007bff;
  color: #fff;
}

/* 英雄区域样式 */
.hero {
  background-color: #f8f9fa;
  padding: 120px 0 80px;
  margin-top: 80px;
}

.hero .container {
  display: flex;
  align-items: center;
  gap: 40px;
}

.hero-content {
  flex: 1;
}

.hero-title {
  font-size: 48px;
  font-weight: bold;
  margin-bottom: 20px;
  line-height: 1.2;
}

.hero-subtitle {
  font-size: 18px;
  color: #666;
  margin-bottom: 30px;
}

.hero-buttons {
  display: flex;
  gap: 20px;
}

.hero-image {
  flex: 1;
}

.hero-image img {
  width: 100%;
  height: auto;
  border-radius: 8px;
}

/* 通用 section 样式 */
.section-title {
  font-size: 36px;
  font-weight: bold;
  text-align: center;
  margin-bottom: 15px;
}

.section-subtitle {
  font-size: 16px;
  color: #666;
  text-align: center;
  margin-bottom: 60px;
}

/* 产品展示样式 */
.products {
  padding: 80px 0;
}

.products-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
  gap: 30px;
}

.product-card {
  background-color: #fff;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s, box-shadow 0.3s;
}

.product-card:hover {
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

.product-title {
  font-size: 20px;
  font-weight: bold;
  margin: 20px;
}

.product-description {
  font-size: 14px;
  color: #666;
  margin: 0 20px 20px;
}

.product-card .btn-secondary {
  margin: 0 20px 20px;
  width: calc(100% - 40px);
}

/* 服务项目样式 */
.services {
  padding: 80px 0;
  background-color: #f8f9fa;
}

.services-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 30px;
}

.service-card {
  background-color: #fff;
  border-radius: 8px;
  padding: 40px;
  text-align: center;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s, box-shadow 0.3s;
}

.service-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
}

.service-icon {
  font-size: 48px;
  color: #007bff;
  margin-bottom: 20px;
}

.service-title {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 15px;
}

.service-description {
  font-size: 14px;
  color: #666;
}

/* 公司优势样式 */
.advantages {
  padding: 80px 0;
}

.advantages-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 30px;
}

.advantage-item {
  text-align: center;
  padding: 40px;
}

.advantage-icon {
  font-size: 48px;
  color: #007bff;
  margin-bottom: 20px;
}

.advantage-title {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 15px;
}

.advantage-description {
  font-size: 14px;
  color: #666;
}

/* 客户案例样式 */
.cases {
  padding: 80px 0;
  background-color: #f8f9fa;
}

.cases-carousel {
  display: flex;
  gap: 30px;
  overflow-x: auto;
  padding-bottom: 20px;
}

.case-item {
  flex: 0 0 400px;
  background-color: #fff;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.case-item img {
  width: 100%;
  height: 250px;
  object-fit: cover;
}

.case-info {
  padding: 20px;
}

.case-info h3 {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 10px;
}

.case-info p {
  font-size: 14px;
  color: #666;
}

/* 联系我们样式 */
.contact {
  padding: 80px 0;
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
  color: #007bff;
  flex-shrink: 0;
}

.contact-item h3 {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 5px;
}

.contact-item p {
  font-size: 14px;
  color: #666;
}

.contact-form {
  flex: 1;
  background-color: #f8f9fa;
  padding: 40px;
  border-radius: 8px;
}

.form-group {
  margin-bottom: 20px;
}

.form-group label {
  display: block;
  font-weight: 500;
  margin-bottom: 10px;
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
  border-color: #007bff;
  box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
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
  color: #fff;
}

.footer-contact {
  flex: 1;
  min-width: 250px;
}

.footer-contact h4 {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 20px;
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
  
  .hero .container {
    flex-direction: column;
    text-align: center;
  }
  
  .hero-title {
    font-size: 36px;
  }
  
  .hero-buttons {
    justify-content: center;
  }
  
  .contact-content {
    flex-direction: column;
  }
  
  .footer-content {
    flex-direction: column;
    align-items: center;
    text-align: center;
  }
  
  .cases-carousel {
    justify-content: center;
  }
}

@media (max-width: 480px) {
  .hero-title {
    font-size: 28px;
  }
  
  .section-title {
    font-size: 28px;
  }
  
  .case-item {
    flex: 0 0 300px;
  }
}
</style>
