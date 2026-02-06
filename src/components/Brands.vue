<template>
  <div>
    <div class="brands-section">
      <div class="brands-container">
        <h2 class="section-title">Involved Brands</h2>
        <p class="section-subtitle">Proud to collaborate with industry-leading partners worldwide</p>
        <div class="brands-slider">
           <div class="brands-track track-1">
             <div v-for="brand in firstRowBrands" :key="brand.name" class="brand-item">
               <div class="brand-logo">
                 <img :src="brand.logo" :alt="brand.name" />
               </div>
               <div class="brand-name">{{ brand.name }}</div>
             </div>
             <div v-for="brand in firstRowBrands" :key="brand.name + '-dup'" class="brand-item">
               <div class="brand-logo">
                 <img :src="brand.logo" :alt="brand.name" />
               </div>
               <div class="brand-name">{{ brand.name }}</div>
             </div>
           </div>
           <div class="brands-track track-2">
             <div v-for="brand in secondRowBrands" :key="brand.name" class="brand-item">
               <div class="brand-logo">
                 <img :src="brand.logo" :alt="brand.name" />
               </div>
               <div class="brand-name">{{ brand.name }}</div>
             </div>
             <div v-for="brand in secondRowBrands" :key="brand.name + '-dup'" class="brand-item">
               <div class="brand-logo">
                 <img :src="brand.logo" :alt="brand.name" />
               </div>
               <div class="brand-name">{{ brand.name }}</div>
             </div>
           </div>
         </div>
         <div class="brands-divider"></div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
const brands = [
  { name: 'YANMAR', logo: '/images/brands/yanmar.png' },
  { name: 'BOBCAT', logo: '/images/brands/bobcat.png' },
  { name: 'CATERPILLAR', logo: '/images/brands/caterpillar.png' },
  { name: 'HYUNDAI', logo: '/images/brands/hyundai.png' },
  { name: 'VOLVO', logo: '/images/brands/volvo.png' },
  { name: 'DOOSAN', logo: '/images/brands/doosan.png' },
  { name: 'JCB', logo: '/images/brands/jcb.png' },
  { name: 'KOBERCO', logo: '/images/brands/koberco.png' },
  { name: 'KUBATA', logo: '/images/brands/kubata.png' },
  { name: 'LIUGONG', logo: '/images/brands/liugong.png' },
  { name: 'SANY', logo: '/images/brands/sany.png' },
  { name: 'SDLG', logo: '/images/brands/sdlg.png' },
  { name: 'SUNWARD', logo: '/images/brands/sunward.png' },
  { name: 'XCMG', logo: '/images/brands/xcmg.png' },
  { name: 'KOMATSU', logo: '/images/brands/komatsu.png' },
  { name: 'FAW TRUCKS', logo: '/images/brands/FAWTRUCKS.png' },
  { name: 'SHACMAN', logo: '/images/brands/SHACMAN.jpg' },
  { name: 'SINOTRUK', logo: '/images/brands/SINOTRUK.jpg' },
]

const firstRowBrands = computed(() => {
  const midpoint = Math.ceil(brands.length / 2)
  return brands.slice(0, midpoint)
})

const secondRowBrands = computed(() => {
  const midpoint = Math.ceil(brands.length / 2)
  return brands.slice(midpoint)
})
</script>

<style scoped>
.brands-section {
  padding: 100px 60px;
  background: #fff;
}

.brands-container {
  max-width: 100%;
  margin: 0 auto;
  text-align: center;
  overflow: hidden;
}

.brands-slider {
  margin-top: 60px;
  position: relative;
}

.brands-track {
  display: flex;
  gap: 30px;
  width: max-content;
}

.track-1 {
  animation: scrollLeft 30s linear infinite;
}

.track-2 {
  animation: scrollRight 30s linear infinite;
  margin-top: 30px;
}
@keyframes scrollLeft {
  0% {
    transform: translateX(0);
  }
  100% {
    transform: translateX(-50%);
  }
}
@keyframes scrollRight {
  0% {
    transform: translateX(0);
  }
  100% {
    transform: translateX(-50%);
  }
}

.brands-slider:hover .brands-track {
  animation-play-state: paused;
}

.brand-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px 30px;
  border: 1px solid #e8e8e8;
  border-radius: 8px;
  transition: all 0.3s ease;
  cursor: pointer;
  background: #fff;
  white-space: nowrap;
}

.brand-item:hover {
  border-color: #FF0000;
  transform: translateY(-5px);
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
}

.brand-logo {
  width: 120px;
  height: 50px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 10px;
}

.brand-logo img {
  max-width: 100%;
  max-height: 100%;
  object-fit: contain;
}

.brand-name {
  font-size: 14px;
  font-weight: 600;
  color: #333;
}

.brands-divider {
  margin-top: 60px;
  border-top: 1px solid #e8e8e8;
}

@media (max-width: 768px) {
  .brands-section {
    padding: 60px 20px;
  }

  .brands-slider {
    margin-top: 40px;
  }

  .brands-track {
    gap: 20px;
  }

  .track-2 {
    margin-top: 20px;
  }

  .brand-item {
    padding: 15px 20px;
  }

  .brand-logo {
    width: 80px;
    height: 40px;
  }

  .brand-name {
    font-size: 12px;
  }
}

@media (min-width: 1920px) {
  .brands-section {
    padding: 140px 80px;
  }

  .brands-slider {
    margin-top: 80px;
  }

  .brands-track {
    gap: 40px;
  }

  .track-2 {
    margin-top: 40px;
  }

  .brand-item {
    padding: 28px 42px;
  }

  .brand-logo {
    width: 160px;
    height: 70px;
  }

  .brand-name {
    font-size: 18px;
  }

  .brands-divider {
    margin-top: 80px;
  }

  .section-title {
    font-size: 48px;
  }

  .section-subtitle {
    font-size: 24px;
  }
}

@media (min-width: 2560px) {
  .brands-section {
    padding: 180px 100px;
  }

  .brands-slider {
    margin-top: 100px;
  }

  .brands-track {
    gap: 50px;
  }

  .track-2 {
    margin-top: 50px;
  }

  .brand-item {
    padding: 36px 54px;
  }

  .brand-logo {
    width: 200px;
    height: 90px;
  }

  .brand-name {
    font-size: 22px;
  }

  .brands-divider {
    margin-top: 100px;
  }

  .section-title {
    font-size: 64px;
  }

  .section-subtitle {
    font-size: 32px;
  }
}
</style>
