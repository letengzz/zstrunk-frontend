import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'
import vueDevTools from 'vite-plugin-vue-devtools'
import VueRouter from 'unplugin-vue-router/vite'
import Layouts from 'vite-plugin-vue-layouts'
// 自动引入注册
import AutoImport from 'unplugin-auto-import/vite'
import { VueRouterAutoImports } from 'unplugin-vue-router'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'
import UnoCSS from 'unocss/vite'// 导入 UnoCSS 插件
// gzip压缩
import viteCompression from 'vite-plugin-compression'
// 图片压缩
import { ViteImageOptimizer } from 'vite-plugin-image-optimizer'

// https://vite.dev/config/
export default defineConfig({
    build: {
    // 10kb以下 转Base64
    assetsInlineLimit: 1024 * 10,
    // chunkSizeWarningLimit:1500, //配置文件大小提醒限制 默认为500
    rollupOptions: {
      output: {
        // 每个node_modules下的文件单独打包
        manualChunks(id: string) {
          if (id.includes('node_modules')) {
            // return 'vendor' //第三方依赖合并在一起
            // 抽离第三方依赖
            // return id.toString().split('node_modules/.pnpm/')[1].split('/')[0].toString()
            return id.toString().split('node_modules/')[1].split('/')[0].toString()
          }
          return undefined
        },
        // 用于从入口点创建的块的打包输出格式[name]表示文件名，[hash]表示该文件hash值
        entryFileNames: 'assets/js/[name].[hash].js', // 用于命名代码拆分时创建的共享的输出命名
        chunkFileNames: 'assets/js/[name].[hash].js', // 用于输出静态资源的命名，[ext]表示文件拓展名
        assetFileNames: 'assets/[ext]/[name].[hash].[ext]',
      },
    },
  },
  plugins: [
    ViteImageOptimizer({
      png: {
        // https://sharp.pixelplumbing.com/api-output#png
        quality: 60,
      },
      jpeg: {
        // https://sharp.pixelplumbing.com/api-output#jpeg
        quality: 60,
      },
      jpg: {
        // https://sharp.pixelplumbing.com/api-output#jpeg
        quality: 60,
      },
    }),
    viteCompression({
      verbose: true, // 默认即可
      disable: false, // 开启压缩(不禁用)，默认即可
      deleteOriginFile: false, // 删除源文件
      threshold: 10240, // 压缩阈值，以字节为单位。如果一个资源比这个值小，它就不会被压缩。默认是 10240
      algorithm: 'gzip', // 压缩算法，默认是 gzip
      ext: '.gz', // 文件类型，默认是 .gz
    }),
    VueRouter({
      routesFolder: [
        {
          src: 'src/views',
        },
      ],
      dts: './types/typed-router.d.ts',
    }),
    Layouts({
      layoutsDirs: 'src/layouts',
      defaultLayout: 'default',
    }),
    // ⚠️ Vue must be placed after VueRouter()
    vue(),
    vueJsx(),
    vueDevTools(),
    AutoImport({
      include: [
        /\.[tj]sx?$/, // .ts, .tsx, .js, .jsx
        /\.vue$/,
        /\.vue\?vue/, // .vue
        /\.md$/, // .md
      ],
      resolvers: [ElementPlusResolver()],
      imports: ['vue', 'pinia', VueRouterAutoImports],
      dts: './types/auto-imports.d.ts',
      dirs: ['src/api/backend/**/*.ts', 'src/utils/**/*.ts'], // 自动导入项目中自定义的API和工具函数
      // eslint 报错解决：'ref' is not defined
      // eslintrc: {
        // 默认 false, true 启用生成。生成一次就可以，避免每次工程启动都生成，一旦生成配置文件之后，最好把 enable 关掉，即改成 false。
        // enabled: true,
        // 否则这个文件每次会在重新加载的时候重新生成，这会导致 eslint 有时会找不到这个文件。当需要更新配置文件的时候，再重新打开
        // filepath: './.eslintrc-auto-import.json' // 默认就是 ./.eslintrc-auto-import.json
        // globalsPropValue: true // 默认 true
      // },
    }),
    Components({
      deep: true,
      directoryAsNamespace: false,
      dts: './types/components.d.ts', // 生成组件类型声明文件的路径
      resolvers: [ElementPlusResolver({ importStyle: 'sass' })],
    }),
    UnoCSS()
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url)),
    },
  },
  css: {
    preprocessorOptions: {
      scss: {
        additionalData: `@use '@/assets/scss/settings/element.scss' as *;`,
      },
    },
  },
  server: {
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, '/api'),
      },
    },
  },
})
