<template>
  <ProductDetailContent :id="id" category="tanker" />
</template>

<script setup lang="ts">
import ProductDetailContent from '../ProductDetailContent.vue'

definePage({
  name: 'truck-detail',
  path: '/truck/:id',
})

const route = useRoute()
const id = Number((route.params as { id: string }).id)
</script>
