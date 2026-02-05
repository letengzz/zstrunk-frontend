<template>
  <div class="top-bar">
    <div class="left-box">
      <div class="logo"><img w40 h40 style="width: 40px; height: 40px;" src="/images/logo.svg" alt="logo"></div>
      <span class="title">
        <router-link to="/" style="text-decoration: none; color: inherit;">zhishun truck</router-link>
      </span>
    </div>

    <div class="right-box">
      <el-menu
        mode="horizontal"
        :ellipsis="false"
        active-text-color="#FF4500"
        class="topbar-menu"
      >
        <el-menu-item index="1">
          <router-link to="/" style="text-decoration: none; color: inherit;">HOME</router-link>
        </el-menu-item>
        <el-sub-menu index="2" popper-class="products-popper" placement="bottom-end">
          <template #title><span class="products-title">PRODUCTS</span></template>
          <div class="products-dropdown">
            <div class="product-row">
              <div class="product-column liquid-power-column">
                <div class="product-category-title">Liquid and Power Transport Trailers</div>
                <div class="liquid-power-items">
                  <router-link to="/products?category=AluminiumFuelTanker" class="product-item">Aluminium Fuel Tanker</router-link>
                  <router-link to="/products?category=CarbonSteelFuelTank Trailer" class="product-item">Carbon Steel Fuel Tank Trailer</router-link>
                  <router-link to="/products?category=BulkCementTrailer" class="product-item">Bulk Cement Trailer</router-link>
                  <router-link to="/products?category=AsphaltTankTrailers" class="product-item">Asphalt Tank Trailers</router-link>
                  <router-link to="/products?category=GasTankerTrailer" class="product-item">Gas Tanker Trailer</router-link>
                  <router-link to="/products?category=StainlessSteelTanker Trailer" class="product-item">Stainless Steel Tanker Trailer</router-link>
                  <router-link to="/products?category=ChemicalTankTrailer" class="product-item">Chemical Tank Trailer</router-link>
                  <router-link to="/products?category=StorageTank" class="product-item">Storage Tank</router-link>
                </div>
              </div>
              <div class="product-column">
                <div class="product-category-title">Container Semi Trailer</div>
                <router-link to="/products?category=SkeletalTrailer" class="product-item">Skeletal Trailer</router-link>
                <router-link to="/products?category=FlatbedTrailer" class="product-item">Flatbed Trailer</router-link>
              </div>
              <div class="product-column semi-trailer-column">
                <div class="product-category-title">Semi Trailer</div>
                <div class="semi-trailer-items">
                  <router-link to="/products?category=LowbedSemiTrailer" class="product-item">Lowbed Semi Trailer</router-link>
                  <router-link to="/products?category=TipperSemiTrailer" class="product-item">Tipper Semi Trailer</router-link>
                  <router-link to="/products?category=FenceCargoTrailer" class="product-item">Fence Cargo Trailer</router-link>
                  <router-link to="/products?category=SideWallTipper" class="product-item">Side Wall Tipper</router-link>
                  <router-link to="/products?category=SideCurtainTrailer" class="product-item">Side Curtain Trailer</router-link>
                  <router-link to="/products?category=CarCarrierTailer" class="product-item">Car Carrier Tailer</router-link>
                  <router-link to="/products?category=BoxSemiTrailer" class="product-item">Box Semi Trailer</router-link>
                  <router-link to="/products?category=FullDrawbarTrailer" class="product-item">Full Drawbar Trailer</router-link>
                  <router-link to="/products?category=RemovableGooseneckTrailer" class="product-item">Removable Gooseneck Trailer</router-link>
                </div>
              </div>
            </div>
            <div class="product-row">
              <div class="product-column">
                <div class="product-category-title">Shacman Trucks</div>
                <router-link to="/products?category=ShacmanDumpTruck" class="product-item">Shacman Dump Truck</router-link>
                <router-link to="/products?category=ShacmanTractorTruck" class="product-item">Shacman Tractor Truck</router-link>
                <router-link to="/products?category=ShacmanTankerTrucks" class="product-item">Shacman Tanker Trucks</router-link>
              </div>
              <div class="product-column">
                <div class="product-category-title">Accessories</div>
                <router-link to="/products?category=TrailerAccessories" class="product-item">Trailer Accessories</router-link>
                <router-link to="/products?category=Engine" class="product-item">Engine</router-link>
              </div>
              <div class="product-column">
                <div class="product-category-title">Sinotruck Howo</div>
                <router-link to="/products?category=HOWOTractorTruck" class="product-item">HOWO Tractor Truck</router-link>
                <router-link to="/products?category=HOWODumpTruck" class="product-item">HOWO Dump Truck</router-link>
                <router-link to="/products?category=HOWOTankerTruck" class="product-item">HOWO Tanker Truck</router-link>
              </div>
              <div class="product-column">
                <div class="product-category-title">Existing Trucks and Trailers</div>
                <router-link to="/products?category=Trailer" class="product-item">Trailer</router-link>
              </div>
              <div class="product-column">
                <div class="product-category-title">Excavators</div>
                <router-link to="/products?category=excavator" class="product-item">Excavator</router-link>
              </div>
            </div>
          </div>
        </el-sub-menu>
        <el-menu-item index="3">
          <router-link to="/contact" style="text-decoration: none; color: inherit;">Request A Quote</router-link>
        </el-menu-item>
        <el-menu-item index="4">
          <router-link to="/about" style="text-decoration: none; color: inherit;">About Us</router-link>
        </el-menu-item>
      </el-menu>

      <button class="mobile-menu-btn" @click="toggleMobileMenu">
        <svg v-if="!mobileMenuOpen" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor">
          <path d="M3 6h18v2H3V6zm0 5h18v2H3v-2zm0 5h18v2H3v-2z"/>
        </svg>
        <svg v-else xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor">
          <path d="M19 6.41L17.59 5 12 10.59 6.41 5 5 6.41 10.59 12 5 17.59 6.41 19 12 13.41 17.59 19 19 17.59 13.41 12z"/>
        </svg>
      </button>
    </div>

    <Transition name="mobile-menu">
      <div v-if="mobileMenuOpen" class="mobile-menu-overlay" @click="closeMobileMenu">
        <div class="mobile-menu-panel" @click.stop>
          <div class="mobile-menu-header">
            <span class="mobile-menu-title">MENU</span>
            <button class="mobile-menu-close" @click="closeMobileMenu">
              <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor">
                <path d="M19 6.41L17.59 5 12 10.59 6.41 5 5 6.41 10.59 12 5 17.59 6.41 19 12 13.41 17.59 19 19 17.59 13.41 12z"/>
              </svg>
            </button>
          </div>
          <div class="mobile-menu-content">
            <router-link to="/" class="mobile-menu-item" @click="closeMobileMenu">HOME</router-link>
            <div class="mobile-menu-group">
              <button class="mobile-menu-group-title" @click="toggleProductsMenu">
                PRODUCTS
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" :class="{ 'rotate': productsMenuOpen }">
                  <path d="M7 10l5 5 5-5z"/>
                </svg>
              </button>
              <Transition name="slide">
                <div v-if="productsMenuOpen" class="mobile-menu-group-content">
                  <div class="mobile-product-section">
                    <div class="mobile-product-title">Liquid and Power Transport</div>
                    <router-link to="/products?category=AluminiumFuelTanker" class="mobile-product-item" @click="closeMobileMenu">Aluminium Fuel Tanker</router-link>
                    <router-link to="/products?category=CarbonSteelFuelTank Trailer" class="mobile-product-item" @click="closeMobileMenu">Carbon Steel Fuel Tank Trailer</router-link>
                    <router-link to="/products?category=BulkCementTrailer" class="mobile-product-item" @click="closeMobileMenu">Bulk Cement Trailer</router-link>
                    <router-link to="/products?category=AsphaltTankTrailers" class="mobile-product-item" @click="closeMobileMenu">Asphalt Tank Trailers</router-link>
                    <router-link to="/products?category=GasTankerTrailer" class="mobile-product-item" @click="closeMobileMenu">Gas Tanker Trailer</router-link>
                    <router-link to="/products?category=StainlessSteelTanker Trailer" class="mobile-product-item" @click="closeMobileMenu">Stainless Steel Tanker Trailer</router-link>
                    <router-link to="/products?category=ChemicalTankTrailer" class="mobile-product-item" @click="closeMobileMenu">Chemical Tank Trailer</router-link>
                    <router-link to="/products?category=StorageTank" class="mobile-product-item" @click="closeMobileMenu">Storage Tank</router-link>
                  </div>
                  <div class="mobile-product-section">
                    <div class="mobile-product-title">Container Semi Trailer</div>
                    <router-link to="/products?category=SkeletalTrailer" class="mobile-product-item" @click="closeMobileMenu">Skeletal Trailer</router-link>
                    <router-link to="/products?category=FlatbedTrailer" class="mobile-product-item" @click="closeMobileMenu">Flatbed Trailer</router-link>
                  </div>
                  <div class="mobile-product-section">
                    <div class="mobile-product-title">Semi Trailer</div>
                    <router-link to="/products?category=LowbedSemiTrailer" class="mobile-product-item" @click="closeMobileMenu">Lowbed Semi Trailer</router-link>
                    <router-link to="/products?category=TipperSemiTrailer" class="mobile-product-item" @click="closeMobileMenu">Tipper Semi Trailer</router-link>
                    <router-link to="/products?category=FenceCargoTrailer" class="mobile-product-item" @click="closeMobileMenu">Fence Cargo Trailer</router-link>
                    <router-link to="/products?category=SideWallTipper" class="mobile-product-item" @click="closeMobileMenu">Side Wall Tipper</router-link>
                    <router-link to="/products?category=SideCurtainTrailer" class="mobile-product-item" @click="closeMobileMenu">Side Curtain Trailer</router-link>
                    <router-link to="/products?category=CarCarrierTailer" class="mobile-product-item" @click="closeMobileMenu">Car Carrier Tailer</router-link>
                    <router-link to="/products?category=BoxSemiTrailer" class="mobile-product-item" @click="closeMobileMenu">Box Semi Trailer</router-link>
                    <router-link to="/products?category=FullDrawbarTrailer" class="mobile-product-item" @click="closeMobileMenu">Full Drawbar Trailer</router-link>
                    <router-link to="/products?category=RemovableGooseneckTrailer" class="mobile-product-item" @click="closeMobileMenu">Removable Gooseneck Trailer</router-link>
                  </div>
                  <div class="mobile-product-section">
                    <div class="mobile-product-title">Shacman Trucks</div>
                    <router-link to="/products?category=ShacmanDumpTruck" class="mobile-product-item" @click="closeMobileMenu">Shacman Dump Truck</router-link>
                    <router-link to="/products?category=ShacmanTractorTruck" class="mobile-product-item" @click="closeMobileMenu">Shacman Tractor Truck</router-link>
                    <router-link to="/products?category=ShacmanTankerTrucks" class="mobile-product-item" @click="closeMobileMenu">Shacman Tanker Trucks</router-link>
                  </div>
                  <div class="mobile-product-section">
                    <div class="mobile-product-title">Accessories</div>
                    <router-link to="/products?category=TrailerAccessories" class="mobile-product-item" @click="closeMobileMenu">Trailer Accessories</router-link>
                    <router-link to="/products?category=Engine" class="mobile-product-item" @click="closeMobileMenu">Engine</router-link>
                  </div>
                  <div class="mobile-product-section">
                    <div class="mobile-product-title">Sinotruck Howo</div>
                    <router-link to="/products?category=HOWOTractorTruck" class="mobile-product-item" @click="closeMobileMenu">HOWO Tractor Truck</router-link>
                    <router-link to="/products?category=HOWODumpTruck" class="mobile-product-item" @click="closeMobileMenu">HOWO Dump Truck</router-link>
                    <router-link to="/products?category=HOWOTankerTruck" class="mobile-product-item" @click="closeMobileMenu">HOWO Tanker Truck</router-link>
                  </div>
                  <div class="mobile-product-section">
                    <div class="mobile-product-title">Existing Trucks and Trailers</div>
                    <router-link to="/products?category=Trailer" class="mobile-product-item" @click="closeMobileMenu">Trailer</router-link>
                  </div>
                  <div class="mobile-product-section">
                    <div class="mobile-product-title">Excavators</div>
                    <router-link to="/products?category=excavator" class="mobile-product-item" @click="closeMobileMenu">Excavator</router-link>
                  </div>
                </div>
              </Transition>
            </div>
            <router-link to="/contact" class="mobile-menu-item" @click="closeMobileMenu">Request A Quote</router-link>
            <router-link to="/about" class="mobile-menu-item" @click="closeMobileMenu">About Us</router-link>
          </div>
        </div>
      </div>
    </Transition>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'

const mobileMenuOpen = ref(false)
const productsMenuOpen = ref(false)

const toggleMobileMenu = () => {
  mobileMenuOpen.value = !mobileMenuOpen.value
  if (!mobileMenuOpen.value) {
    productsMenuOpen.value = false
  }
}

const closeMobileMenu = () => {
  mobileMenuOpen.value = false
  productsMenuOpen.value = false
}

const toggleProductsMenu = () => {
  productsMenuOpen.value = !productsMenuOpen.value
}
</script>

<style scoped lang="scss">
.top-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 40px;
  height: 100px;

  .left-box {
    display: flex;
    align-items: center;
    gap: 15px;
    padding: 0 30px;
    height: 60px;
    background: rgba(255, 255, 255, 0.15);
    backdrop-filter: blur(10px);
    -webkit-backdrop-filter: blur(10px);
    border-radius: 15px;
    border: 1px solid rgba(255, 255, 255, 0.2);
    box-shadow:
      0 8px 32px 0 rgba(31, 38, 135, 0.15),
      inset 0 0 0 1px rgba(255, 255, 255, 0.1);
    transition: all 0.3s ease;

    .logo {
      font-size: 24px;
      font-weight: bold;
      color: #ffffff;
      text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
    }

    .title {
      font-size: 28px;
      font-weight: 700;
      color: #ffffff;
      letter-spacing: 2px;
      text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
    }

    &:hover {
      background: rgba(255, 255, 255, 0.95);
      box-shadow:
        0 8px 32px 0 rgba(31, 38, 135, 0.2),
        inset 0 0 0 1px rgba(255, 255, 255, 0.3);

      .logo {
        color: #495057;
        text-shadow: none;
      }

      .title {
        color: #495057;
        text-shadow: none;
      }
    }
  }

  .right-box {
    display: flex;
    align-items: center;
    padding: 0 30px;
    height: 60px;
    background: rgba(255, 255, 255, 0.15);
    backdrop-filter: blur(10px);
    -webkit-backdrop-filter: blur(10px);
    border-radius: 15px;
    border: 1px solid rgba(255, 255, 255, 0.2);
    box-shadow:
      0 8px 32px 0 rgba(31, 38, 135, 0.15),
      inset 0 0 0 1px rgba(255, 255, 255, 0.1);
    transition: all 0.3s ease;

    &:hover {
      background: rgba(255, 255, 255, 0.95);
      box-shadow:
        0 8px 32px 0 rgba(31, 38, 135, 0.2),
        inset 0 0 0 1px rgba(255, 255, 255, 0.3);
    }

    .topbar-menu {
      background: transparent !important;
      border: none !important;
      display: flex;
      justify-content: center;
      align-items: center;
    }

    :deep(.topbar-menu .el-menu-item),
    :deep(.topbar-menu .el-sub-menu__title) {
      color: #ffffff !important;
      font-size: 18px;
      font-weight: 500;
      height: 60px;
      line-height: 60px;
      text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
    }

    :deep(.topbar-menu .el-sub-menu__title:hover) {
      background: rgba(255, 255, 255, 0.1) !important;
    }

    :deep(.topbar-menu .el-menu-item:hover) {
      background: rgba(255, 255, 255, 0.1) !important;
    }

    &:hover {
      :deep(.topbar-menu .el-menu-item),
      :deep(.topbar-menu .el-sub-menu__title) {
        color: #495057 !important;
        text-shadow: none;
      }
    }

    .mobile-menu-btn {
      display: none;
      width: 44px;
      height: 44px;
      padding: 8px;
      background: rgba(255, 255, 255, 0.15);
      border: 1px solid rgba(255, 255, 255, 0.3);
      border-radius: 10px;
      cursor: pointer;
      color: #ffffff;
      transition: all 0.3s ease;

      svg {
        width: 100%;
        height: 100%;
      }

      &:hover {
        background: rgba(255, 255, 255, 0.25);
      }
    }
  }
}

.mobile-menu-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
  z-index: 9999;
  display: flex;
  justify-content: flex-end;
}

.mobile-menu-panel {
  width: 85%;
  max-width: 320px;
  height: 100%;
  background: #ffffff;
  display: flex;
  flex-direction: column;
  box-shadow: -4px 0 20px rgba(0, 0, 0, 0.15);
}

.mobile-menu-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px;
  border-bottom: 1px solid #e8e8e8;
}

.mobile-menu-title {
  font-size: 18px;
  font-weight: 700;
  color: #333;
}

.mobile-menu-close {
  width: 36px;
  height: 36px;
  padding: 6px;
  background: #f5f5f5;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  color: #666;

  svg {
    width: 100%;
    height: 100%;
  }
}

.mobile-menu-content {
  flex: 1;
  overflow-y: auto;
  padding: 15px 0;
}

.mobile-menu-item {
  display: block;
  padding: 15px 20px;
  color: #333;
  text-decoration: none;
  font-size: 16px;
  font-weight: 500;
  transition: all 0.2s ease;

  &:hover {
    background: #f5f5f5;
    color: #FF4500;
  }
}

.mobile-menu-group {
  border-bottom: 1px solid #f0f0f0;
}

.mobile-menu-group-title {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  padding: 15px 20px;
  background: none;
  border: none;
  font-size: 16px;
  font-weight: 500;
  color: #333;
  cursor: pointer;
  transition: all 0.2s ease;

  svg {
    width: 20px;
    height: 20px;
    transition: transform 0.3s ease;
  }

  svg.rotate {
    transform: rotate(180deg);
  }

  &:hover {
    background: #f5f5f5;
    color: #FF4500;
  }
}

.mobile-menu-group-content {
  background: #fafafa;
}

.mobile-product-section {
  padding: 10px 20px;
  border-bottom: 1px solid #f0f0f0;
}

.mobile-product-title {
  font-size: 13px;
  font-weight: 600;
  color: #FF4500;
  padding: 8px 0;
}

.mobile-product-item {
  display: block;
  padding: 8px 0 8px 10px;
  font-size: 14px;
  color: #666;
  text-decoration: none;
  border-left: 2px solid transparent;
  transition: all 0.2s ease;

  &:hover {
    color: #FF4500;
    border-left-color: #FF4500;
  }
}

.mobile-menu-enter-active,
.mobile-menu-leave-active {
  transition: opacity 0.3s ease;
}

.mobile-menu-enter-active .mobile-menu-panel,
.mobile-menu-leave-active .mobile-menu-panel {
  transition: transform 0.3s ease;
}

.mobile-menu-enter-from,
.mobile-menu-leave-to {
  opacity: 0;
}

.mobile-menu-enter-from .mobile-menu-panel,
.mobile-menu-leave-to .mobile-menu-panel {
  transform: translateX(100%);
}

.slide-enter-active,
.slide-leave-active {
  transition: all 0.3s ease;
}

.slide-enter-from,
.slide-leave-to {
  opacity: 0;
  transform: translateY(-10px);
}

.products-dropdown {
  display: flex;
  flex-direction: column;
  gap: 20px;
  padding: 10px;
  min-width: 700px;
}

.product-row {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 20px;
}

.product-column {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.product-category-title {
  font-weight: 700;
  font-size: 14px;
  color: #333;
  padding-bottom: 8px;
  border-bottom: 2px solid #FF4500;
  margin-bottom: 5px;
  white-space: nowrap;
}

.product-item {
  font-size: 13px;
  color: #666;
  text-decoration: none;
  cursor: pointer;
  padding: 4px 8px;
  border-radius: 4px;
  transition: color 0.05s ease, background 0.05s ease, outline 0.05s ease;
  display: inline-block;
  position: relative;
}

.product-item:hover,
.product-item:focus,
.product-item:active {
  color: #FF4500;
  background: rgba(255, 69, 0, 0.1);
  outline: none;
}

.product-item.router-link-active {
  color: #666;
  background: transparent;
}

.product-item:any-link {
  color: #666;
}

.semi-trailer-items {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 4px 12px;
}

.liquid-power-items {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 4px 12px;
}

.products-dropdown:hover .product-item:hover:not(:hover) {
  color: #666 !important;
  background: transparent !important;
  outline: none !important;
}

:global(.products-popper) {
  left: auto !important;
  right: 0 !important;
}

:global(.products-popper .el-menu-item) {
  outline: none !important;
  background: transparent !important;
  transition: none !important;
}

:global(.products-popper .el-menu-item:focus) {
  outline: none !important;
  background: transparent !important;
  color: #666 !important;
}

:global(.products-popper .el-menu-item:hover) {
  outline: none !important;
  background: transparent !important;
}

:global(.products-popper .el-menu-item a) {
  outline: none !important;
  background: transparent !important;
}

:global(.products-popper:hover .el-menu-item:focus) {
  color: #666 !important;
  background: transparent !important;
  outline: none !important;
}

:global(.products-dropdown:hover .product-item) {
  color: #666 !important;
  background: transparent !important;
  outline: none !important;
}

:global(.products-dropdown:hover .product-item:hover) {
  color: #FF4500 !important;
  background: rgba(255, 69, 0, 0.1) !important;
  outline: none !important;
}

@media (max-width: 768px) {
  .top-bar {
    padding: 0 15px;
    height: 70px;

    .left-box {
      padding: 0 15px;
      height: 45px;
      gap: 10px;

      .logo {
        font-size: 20px;
      }

      .title {
        font-size: 18px;
      }
    }

    .right-box {
      padding: 0px;
      height: auto;

      .topbar-menu {
        display: none;
      }

      .mobile-menu-btn {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 40px;
        height: 40px;
        padding: 6px;
        background: rgba(255, 255, 255, 0.15);
        border: 1px solid rgba(255, 255, 255, 0.3);
        border-radius: 8px;
        cursor: pointer;
        color: #ffffff;
        transition: all 0.3s ease;

        svg {
          width: 100%;
          height: 100%;
        }

        &:hover {
          background: rgba(255, 255, 255, 0.25);
        }
      }
    }
  }
}
</style>
