<template>
  <ProductDetailContent :id="id" category="excavator" />
</template>

<script setup lang="ts">
import ProductDetailContent from '../ProductDetailContent.vue'

definePage({
  name: 'excavator-detail',
  path: '/excavator/:id',
})

const route = useRoute()
const id = Number((route.params as { id: string }).id)
</script>
