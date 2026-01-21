<template>
  <div>
    <el-carousel
      ref="carouselRef"
      indicator-position="none"
      arrow="never"
      :autoplay="true"
            @mousedown="handleMouseDown"
      @mousemove="handleMouseMove"
      @mouseup="handleMouseUp"
      @mouseleave="handleMouseUp"
    >
      <el-carousel-item v-for="(item, index) in carouselData" :key="index">
        <!-- <div class="carousel-item-wrapper"> -->
          <img :src="item.image" alt="轮播图" class="carousel-image" />
          <!-- <div class="overlay"></div>
          <div class="content-wrapper">
            <h1 class="main-title">{{ item.title }}</h1>
            <p class="sub-title">{{ item.subtitle }}</p>
            <button class="cta-button">{{ item.buttonText }}</button> -->
          <!-- </div> -->
        <!-- </div> -->
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script setup lang="ts">
const carouselData = [
  {
    image: '/images/carousel/carousel1.png',
    title: 'Expert Car Parts & Repair',
    subtitle: 'Professional auto repair services with quality parts',
    buttonText: 'Get Started'
  },
  {
    image: '/images/carousel/carousel2.png',
    title: 'Quality Service You Trust',
    subtitle: 'Experienced mechanics ready to help you',
    buttonText: 'Book Now'
  }
]
let startX = 0
let isDragging = false
const minSwipeDistance = 50
const carouselRef = ref()
const handleMouseDown = (e: MouseEvent) => {
  startX = e.clientX
  isDragging = true
}

const handleMouseMove = (e: MouseEvent) => {
  if (!isDragging) return

  const currentX = e.clientX
  const diffX = currentX - startX

  if (Math.abs(diffX) >= minSwipeDistance) {
    if (diffX > 0) {
      carouselRef.value?.prev()
    } else {
      carouselRef.value?.next()
    }
    isDragging = false
  }
}

const handleMouseUp = () => {
  isDragging = false
}
</script>

<style scoped lang="scss">
/* .carousel-item-wrapper {
  width: 100%;
  height: 100%;
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  overflow: hidden;
}

.carousel-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
  object-position: center;
}

:deep(.el-carousel__container){
  height:800px;
}
.overlay {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.6);
}
.main-title {
  font-size: 64px;
  font-weight: 700;
  margin-bottom: 20px;
  line-height: 1.2;
  text-transform: uppercase;
  letter-spacing: 2px;
}
.sub-title {
  font-size: 20px;
  margin-bottom: 40px;
  opacity: 0.9;
  line-height: 1.6;
} */
// .carousel-item-wrapper {
//   position: relative;
//   width: 100%;
//   height: 180vh;
//   overflow: hidden;
// }

// // .carousel-image {
// //   width: 100%;
// //   height: 100%;
// //   object-fit: cover;
// // }

// .overlay {
//   position: absolute;
//   top: 0;
//   left: 0;
//   right: 0;
//   bottom: 0;
//   background: rgba(0, 0, 0, 0.6);
// }

// .content-wrapper {
//   position: absolute;
//   top: 50%;
//   left: 50%;
//   transform: translate(-50%, -50%);
//   text-align: center;
//   color: #fff;
//   max-width: 800px;
//   padding: 20px;
// }

// .main-title {
//   font-size: 64px;
//   font-weight: 700;
//   margin-bottom: 20px;
//   line-height: 1.2;
//   text-transform: uppercase;
//   letter-spacing: 2px;
// }

// .sub-title {
//   font-size: 20px;
//   margin-bottom: 40px;
//   opacity: 0.9;
//   line-height: 1.6;
// }

// .cta-button {
//   background: #fff;
//   color: #000;
//   border: none;
//   padding: 16px 48px;
//   font-size: 16px;
//   font-weight: 600;
//   text-transform: uppercase;
//   letter-spacing: 1px;
//   cursor: pointer;
//   transition: all 0.3s ease;

//   &:hover {
//     background: #f0f0f0;
//     transform: translateY(-2px);
//   }
// }
// :deep(.el-carousel__container){
//   height: 800px;
// }
</style>
