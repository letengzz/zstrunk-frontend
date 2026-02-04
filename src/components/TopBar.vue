<template>
  <div class="top-bar">
    <div class="left-box">
      <div class="logo"><img w40 h40  src="/public/images/logo.png" alt="logo"></div>
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
          <router-link to="/contact" style="text-decoration: none; color: inherit;">Requct A Quote</router-link>
        </el-menu-item>
        <!-- <el-sub-menu index="3">
          <template #title>Excavator</template>
          <el-menu-item index="2-1">Maintenance</el-menu-item>
          <el-menu-item index="2-2">Parts Replacement</el-menu-item>
          <el-menu-item index="2-3">Inspection Services</el-menu-item>
        </el-sub-menu> -->
        <el-menu-item index="4">
          <router-link to="/about" style="text-decoration: none; color: inherit;">About Us</router-link>
        </el-menu-item>
        <!-- <el-menu-item index="5">
          <router-link to="/contact" style="text-decoration: none; color: inherit;">Contact Us</router-link>
        </el-menu-item> -->
        <!-- <el-sub-menu index="3"> -->
          <!-- <template #title>About Us</template> -->
          <!-- <el-menu-item index="2-1">
            <router-link to="/about" style="text-decoration: none; color: inherit;">Company Profile</router-link>
          </el-menu-item>
          <el-menu-item index="2-2">Contact Us</el-menu-item>
          <el-menu-item index="2-3">Join Us</el-menu-item> -->
        <!-- </el-sub-menu> -->
      </el-menu>
    </div>
  </div>
</template>

<script setup lang="ts">

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
  }
}



// :global(.el-popper.is-light .el-menu) {
//   background: transparent !important;
//   border-radius: 0 !important;
// }

// :global(.el-popper.is-light .el-menu--popup) {
//   background: transparent !important;
//   border-radius: 15px !important;
//   padding: 10px 0 !important;
//   margin: 0 !important;
//   min-width: 150px !important;
//   width: auto !important;
// }

// :global(.el-popper.is-light .el-menu--popup-container) {
//   border-radius: 15px !important;
//   background: transparent !important;
// }

// :global(.el-popper.is-light .el-menu-item) {
//   color: #333333 !important;
//   border-radius: 15px !important;
//   margin: 4px 12px !important;
//   padding: 0 20px !important;
//   min-height: 40px !important;
//   line-height: 40px !important;
// }

// :global(.el-popper.is-light .el-menu-item:hover) {
//   background: rgba(64, 158, 255, 0.1) !important;
// }



// :global(.el-sub-menu .el-popper .el-menu) {
//   border-radius: 15px !important;
//   overflow: hidden !important;
// }

// :global(.el-sub-menu .el-popper .el-menu .el-menu-item) {
//   border-radius: 15px !important;
// }

// :global(.el-tooltip__trigger:focus-visible) {
//   outline: none !important;
// }

// :global(.el-sub-menu__title) {
//   border-radius: 0 !important;
// }

/*.products-title {
  font-weight: 700;
  font-size: 18px;
} */


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

// Ensure only one item maintains hover state at a time
.products-dropdown:hover .product-item:hover:not(:hover) {
  color: #666 !important;
  background: transparent !important;
  outline: none !important;
}

:global(.products-popper) {
  // padding: 20px !important;
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
</style>
