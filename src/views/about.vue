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
          <img src="/public/images/about/about-factory.png" alt="Our Factory" />
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

    <div class="historical-section">
      <div class="historical-container">
        <h2 class="section-title">Historical Steps</h2>
        <p class="section-subtitle">Without epic story,from small to large,step by step to win the trust of our customers is what we have been doing.</p>
        <div class="historical-content">
          <div class="historical-display">
            <div class="display-left">
              <div class="display-year">{{ currentHistorical?.year }}</div>
              <div class="display-desc">{{ currentHistorical?.desc }}</div>
            </div>
            <div class="display-right">
              <LazyImage :src="currentHistorical?.image || ''" alt="Historical moment" class="display-image" />
            </div>
          </div>
          <div class="historical-timeline">
            <div
              v-for="(item, index) in historicalData"
              :key="item.year"
              class="timeline-dot"
              :class="{ active: currentHistoricalIndex === index }"
              @click="currentHistoricalIndex = index"
            >
              <div class="dot"></div>
              <div class="dot-year">{{ item.year }}</div>
            </div>
            <div class="timeline-line">
              <div class="timeline-progress" :style="{ width: `${(currentHistoricalIndex / (historicalData.length - 1)) * 100}%` }"></div>
            </div>
          </div>
        </div>
      </div>
    </div>



    <Footer />

    <ContactFixed />

    <CustomerService />
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'
import TopBar from '@/components/TopBar.vue'
import Footer from '@/components/Footer.vue'
import ContactFixed from '@/components/ContactFixed.vue'
import LazyImage from '@/components/LazyImage.vue'

const currentHistoricalIndex = ref(0)

const currentHistorical = computed(() => {
  return historicalData[currentHistoricalIndex.value]
})

const historicalData = [
  {
    year: '2004',
    title: 'Company Founded',
    desc: 'The first important point of QiaoYuan. We started to create our own standard on collecting used machines.',
    image: '/images/about/2004.png'
  },
  {
    year: '2010',
    title: 'First Overseas Expansion',
    desc: 'The first year we began to deliver our first machine to a foreign country.',
    image: '/images/about/2009.png'
  },
  {
    year: '2015',
    title: 'Production Capacity Milestone',
    desc: 'Improve the standards for collecting machines, build our own warehouse, and ensure the quality of each machine',
    image: '/images/about/2014.png'
  },
  {
    year: '2020',
    title: 'Global Recognition',
    desc: 'Within only one year.We sold more then 2000 pcs of construction equipment.',
    image: '/images/about/2024.png'
  },
  {
    year: 'Future',
    title: 'Building Tomorrow',
    desc: 'From the first year we have been focusing on customer’s feeling about what they purchased from us. We won’t change that forever.',
    image: '/images/about/future.png'
  }
]


</script>

<style scoped>
.about-page {
  min-height: 100vh;
  background: #F6F5ED;
}

/* .floating-topbar {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 1000;
} */
.floating-topbar {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  z-index: 100;
  /* background: rgba(255, 255, 255, 0.1);
  backdrop-filter: blur(10px);
  -webkit-backdrop-filter: blur(10px); */
}

.hero-section {
  position: relative;
  height: 400px;
  background: url('/public/images/about/image.png') center/cover no-repeat;
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
  background: linear-gradient(180deg, rgba(26, 42, 74, 0.6) 0%, rgba(26, 42, 74, 0.4) 50%, rgba(139, 0, 0, 0.4) 100%);
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
  text-shadow: 2px 2px 10px rgba(0, 0, 0, 0.5);
}

.hero-subtitle {
  font-size: 20px;
  color: #ffffff;
  margin: 0;
  font-weight: 400;
  letter-spacing: 2px;
  text-shadow: 1px 1px 6px rgba(0, 0, 0, 0.4);
}

.about-section {
  padding: 100px 40px;
  background: #F6F5ED;
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
  color: #1a2a4a;
  text-align: left;
  margin: 0 0 24px 0;
}

.section-subtitle {
  font-size: 16px;
  font-weight: 400;
  color: #4a5568;
  text-align: center;
  margin: 0 0 40px 0;
}

.about-text {
  font-size: 16px;
  color: #4a5568;
  line-height: 1.8;
  margin: 0 0 20px 0;
}

.about-image {
  position: relative;
  /* border-radius: 16px; */
  overflow: hidden;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
}

.about-image img {
  width: 100%;
  height: 400px;
  object-fit: cover;
}

.stats-section {
  padding: 80px 40px;
  background: #ffffff;
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
  padding: 30px;
  background: #F6F5ED;
  border-radius: 16px;
  transition: all 0.3s ease;
}

.stat-item:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 30px rgba(255, 0, 0, 0.15);
}

.stat-number {
  font-size: 48px;
  font-weight: 700;
  color: #FF0000;
  margin-bottom: 10px;
}

.stat-label {
  font-size: 16px;
  color: #4a5568;
}

.features-section {
  padding: 100px 40px;
  background: #F6F5ED;
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
  background: #ffffff;
  border: 1px solid #e2e8f0;
  border-radius: 16px;
  padding: 30px;
  text-align: center;
  transition: all 0.3s ease;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.06);
}

.feature-card:hover {
  transform: translateY(-8px);
  border-color: #FF0000;
  box-shadow: 0 12px 40px rgba(255, 0, 0, 0.15);
}

.feature-icon {
  width: 60px;
  height: 60px;
  margin: 0 auto 20px;
  color: #FF0000;
}

.feature-icon svg {
  width: 100%;
  height: 100%;
}

.feature-title {
  font-size: 20px;
  font-weight: 600;
  color: #1a2a4a;
  margin: 0 0 12px 0;
}

.feature-desc {
  font-size: 14px;
  color: #4a5568;
  line-height: 1.6;
  margin: 0;
}

.process-section {
  padding: 100px 40px;
  background: #ffffff;
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
  background: #F6F5ED;
  border: 1px solid #e2e8f0;
  border-radius: 16px;
  transition: all 0.3s ease;
}

.process-step:hover {
  transform: translateY(-8px);
  border-color: #FF0000;
  box-shadow: 0 12px 40px rgba(255, 0, 0, 0.15);
}

.step-number {
  font-size: 48px;
  font-weight: 700;
  color: rgba(255, 0, 0, 0.2);
  margin-bottom: 20px;
}

.step-title {
  font-size: 18px;
  font-weight: 600;
  color: #1a2a4a;
  margin: 0 0 12px 0;
}

.step-desc {
  font-size: 14px;
  color: #4a5568;
  line-height: 1.6;
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

.historical-section {
  background: url('/images/about/history.png') no-repeat center top;
  /* background: linear-gradient(180deg, rgba(255, 255, 255, 0.95) 0%, rgba(255, 255, 255, 0.95) 100%), url('/images/about/history.png') center center no-repeat; */
  background-size: cover;
  padding: 100px 40px;
}

.historical-container {
  max-width: 1400px;
  margin: 0 auto;
}

.historical-content {
  display: flex;
  flex-direction: column;
  gap: 40px;
}

.historical-display {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 60px;
  align-items: center;
  padding: 0;
  background: transparent;
  border-radius: 0;
  box-shadow: none;
}

.display-left {
  text-align: left;
}

.display-year {
  font-size: 80px;
  font-weight: 800;
  color: #1a2a4a;
  line-height: 1;
  margin-bottom: 20px;
}

.display-desc {
  font-size: 16px;
  color: #4a5568;
  line-height: 1.8;
  margin: 0;
  position: relative;
  padding-left: 24px;
}

.display-desc::before {
  content: '';
  position: absolute;
  left: 0;
  top: 8px;
  width: 8px;
  height: 8px;
  background: #FF0000;
  border-radius: 50%;
}

.display-right {
  overflow: hidden;
  border-radius: 8px;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
}

.display-image {
  width: 100%;
  height: auto;
  display: block;
  object-fit: cover;
}

.historical-timeline {
  display: flex;
  align-items: flex-start;
  justify-content: center;
  gap: 0;
  position: relative;
  padding: 50px 60px;
  background: transparent;
  border-radius: 0;
  box-shadow: none;
}

.timeline-dot {
  display: flex;
  flex-direction: column;
  align-items: center;
  cursor: pointer;
  position: relative;
  z-index: 1;
  width: 80px;
  transition: all 0.3s ease;
}

.timeline-dot:hover {
  background: transparent;
}

.timeline-dot.active {
  background: transparent;
}

.dot {
  width: 12px;
  height: 12px;
  border-radius: 50%;
  background: #ffffff;
  border: 2px solid #e0ddd5;
  transition: all 0.3s ease;
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
}

.timeline-dot.active .dot {
  background: #FF0000;
  border: 2px solid #FF0000;
  transform: translateY(-50%);
  box-shadow: 0 0 0 3px rgba(255, 0, 0, 0.2);
}

.timeline-dot:hover .dot {
  border-color: #FF0000;
}

.dot-year {
  font-size: 14px;
  font-weight: 600;
  color: #666666;
  transition: all 0.3s ease;
  margin-top: 70px;
}

.timeline-dot.active .dot-year {
  color: #FF0000;
  font-weight: 700;
}

.timeline-line {
  position: absolute;
  left: 100px;
  right: 100px;
  height: 3px;
  background: #e0ddd5;
  top: 50%;
  transform: translateY(-50%);
  z-index: 0;
  border-radius: 2px;
}

.timeline-progress {
  height: 100%;
  background: linear-gradient(90deg, #FF0000 0%, #FF0000 100%);
  border-radius: 2px;
  transition: width 0.4s ease;
}







@media (max-width: 768px) {
  .historical-section {
    padding: 60px 20px;
  }

  .historical-display {
    grid-template-columns: 1fr;
    gap: 30px;
    padding: 30px;
  }

  .display-year {
    font-size: 56px;
  }

  .display-title {
    font-size: 22px;
  }

  .display-desc {
    font-size: 14px;
  }

  .historical-timeline {
    flex-wrap: wrap;
    padding: 30px 20px;
    gap: 12px;
  }

  .timeline-dot {
    padding: 10px 16px;
  }

  .dot {
    width: 14px;
    height: 14px;
  }

  .dot-year {
    font-size: 13px;
    margin-top: 8px;
  }

  .timeline-dot {
    flex-direction: row;
    gap: 8px;
  }

  .timeline-dot .dot-year {
    margin-top: 0;
  }

  .timeline-line {
    display: none;
  }

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
