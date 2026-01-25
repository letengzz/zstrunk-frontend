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
        <el-sub-menu index="1">
          <template #title>Trunk</template>
          <el-menu-item index="1-1">Product List</el-menu-item>
          <el-menu-item index="1-2">New Arrivals</el-menu-item>
          <el-menu-item index="1-3">Hot Products</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="2">
          <template #title>Excavator</template>
          <el-menu-item index="2-1">Maintenance</el-menu-item>
          <el-menu-item index="2-2">Parts Replacement</el-menu-item>
          <el-menu-item index="2-3">Inspection Services</el-menu-item>
        </el-sub-menu>
        <el-menu-item index="3-1">
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

:global(.el-popper.is-light) {
  background: rgba(255, 255, 255, 0.95) !important;
  backdrop-filter: blur(10px) !important;
  -webkit-backdrop-filter: blur(10px) !important;
  border: 1px solid rgba(255, 255, 255, 0.3) !important;
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.15) !important;
  border-radius: 15px !important;
  // padding: 10px 0 !important;
  overflow: hidden !important;
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

:global(.el-sub-menu .el-popper) {
  border-radius: 15px !important;
}

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
</style>
