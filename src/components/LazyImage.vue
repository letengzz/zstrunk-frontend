<script setup lang="ts">
import { ref, computed, onMounted, onUnmounted } from 'vue'
import { useIntersectionObserver } from '@vueuse/core'

const props = withDefaults(defineProps<{
  src: string
  alt?: string
  placeholder?: string
  threshold?: number
}>(), {
  alt: '',
  threshold: 0
})

const imageRef = ref<HTMLImageElement | null>(null)
const isLoaded = ref(false)
const hasError = ref(false)
const isInView = ref(false)
let timeoutId: ReturnType<typeof setTimeout> | null = null

onMounted(() => {
  // console.log('🟢 LazyImage mounted:', props.src)

  timeoutId = setTimeout(() => {
    if (!isInView.value) {
      // console.log('⏰ 超时触发，强制加载图片:', props.src)
      isInView.value = true
    }
  }, 2000)
})

onUnmounted(() => {
  if (timeoutId) {
    clearTimeout(timeoutId)
  }
})

const { stop } = useIntersectionObserver(
  imageRef,
  (entries) => {
    const entry = entries[0]
    // console.log('🔵 Observer triggered:', props.src, 'isIntersecting:', entry?.isIntersecting)
    if (entry?.isIntersecting && props.src) {
      // console.log('✅ 进入视口，加载图片:', props.src)
      isInView.value = true
      stop()
    }
  },
  { threshold: props.threshold }
)

const showImage = computed(() => {
  return isInView.value
})

const displayedSrc = computed(() => {
  if (!isInView.value && props.placeholder) {
    return props.placeholder
  }
  if (!isInView.value) {
    return ''
  }
  return props.src
})

const onLoad = () => {
  // console.log('✅ 图片加载成功:', props.src)
  isLoaded.value = true
}

const onError = (e: Event) => {
  console.log('❌ 图片加载失败:', props.src, e)
  hasError.value = true
  isLoaded.value = true
}
</script>

<template>
  <div class="lazy-image-wrapper" :class="{ 'is-loaded': isLoaded }">
    <img
      v-if="showImage"
      ref="imageRef"
      :src="displayedSrc"
      :alt="alt"
      :class="{ 'fade-in': isLoaded }"
      @load="onLoad"
      @error="onError"
    />
    <div v-if="!isLoaded && placeholder" class="placeholder" :style="{ backgroundImage: `url(${placeholder})` }"></div>
    <div v-if="hasError" class="error-placeholder">
      <slot name="error">Image failed to load</slot>
    </div>
  </div>
</template>

<style scoped>
.lazy-image-wrapper {
  position: relative;
  width: 100%;
  height: 100%;
  min-height: 150px;
  overflow: hidden;
  background: #f5f5f5;
}

.placeholder {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-size: cover;
  background-position: center;
  filter: blur(8px);
  transform: scale(1.1);
  transition: opacity 0.3s ease;
}

.lazy-image-wrapper.is-loaded .placeholder {
  opacity: 0;
}

img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  opacity: 0;
  transition: opacity 0.4s ease;
  display: block;
}

img.fade-in {
  opacity: 1;
}

.error-placeholder {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  background: #f0f0f0;
  color: #999;
  font-size: 14px;
}
</style>
