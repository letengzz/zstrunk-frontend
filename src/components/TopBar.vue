<template>
  <div class="top-bar">
    <div class="left-box">
      <div class="logo"><img w40 h40  src="/public/images/logo.png" alt="logo"></div>
      <span class="title">
         <router-link to="/" style="text-decoration: none; color: inherit;">ZHI SHUN</router-link>
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
              <div class="product-column">
                <div class="product-category-title">Liquid and Power Transport Trailers</div>
                <div class="product-item">Aluminium Fuel Tanker</div>
                <div class="product-item">Carbon Steel Fuel Tank Trailer</div>
                <div class="product-item">Bulk Cement Trailer</div>
                <div class="product-item">Asphalt Tank Trailers</div>
                <div class="product-item">Gas Tanker Trailer</div>
                <div class="product-item">Stainless Steel Tanker Trailer</div>
                <div class="product-item">Chemical Tank Trailer</div>
                <div class="product-item">Storage Tank</div>
              </div>
              <div class="product-column">
                <div class="product-category-title">Container Semi Trailer</div>
                <div class="product-item">Skeletal Trailer</div>
                <div class="product-item">Flatbed Trailer</div>
              </div>
              <div class="product-column">
                <div class="product-category-title">Semi Trailer</div>
                <div class="product-item">Container Semi Trailer</div>
                <div class="product-item">Sinotruk Howo</div>
                <div class="product-item">HOWO Tractor Truck</div>
                <div class="product-item">HOWO Dump Truck</div>
                <div class="product-item">HOWO Tanker Truck</div>
              </div>
            </div>
            <div class="product-row">
              <div class="product-column">
                <div class="product-category-title">Shacman Trucks</div>
                <div class="product-item">Shacman Dump Truck</div>
                <div class="product-item">Shacman Tractor Truck</div>
                <div class="product-item">Shacman Tanker Trucks</div>
              </div>
              <div class="product-column">
                <div class="product-category-title">Accessories</div>
                <div class="product-item">Trailer Accessories</div>
                <div class="product-item">Engine</div>
              </div>
              <div class="product-column">
                <div class="product-category-title">Existing Trucks and Trailers</div>
                <div class="product-item">Trailer</div>
              </div>
            </div>
          </div>
        </el-sub-menu>
          <el-menu-item index="3">
          <router-link to="/" style="text-decoration: none; color: inherit;">Requct A Quote</router-link>
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

.products-title {
  font-weight: 700;
  font-size: 18px;
}


.products-dropdown {
  display: flex;
  flex-direction: column;
  gap: 20px;
  padding: 10px;
  min-width: 900px;
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
  cursor: pointer;
  padding: 4px 8px;
  border-radius: 4px;
  transition: all 0.2s ease;
}

.product-item:hover {
  color: #FF4500;
  background: rgba(255, 69, 0, 0.1);
}

:global(.products-popper) {
  // padding: 20px !important;
  left: auto !important;
  right: 0 !important;
}
</style>
