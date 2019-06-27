import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: 'Dashboard', icon: 'dashboard' }
    }]
  },



  {
    path: '/menu',
    component: Layout,
    meta: {
      title: '系统管理',
      icon: 'nested'
    },

    children: [
      {
        path: '/menu1',
        name: 'Menu1',
        meta: { title: '系统管理' },
        component: () => import('@/views/menu/menu1'),
        children: [
          {
            path: '/menu1-1',
            name: 'Menu1-1',
            meta: { title: '权限管理' },
            component: () => import('@/views/menu/menu1/menu1-1')
          },
          {
            path: '/menu1-2',
            component: () => import('@/views/menu/menu1/menu1-2/complex-table'),
            name: 'Menu1-2',
            meta: { title: '角色管理' }
          }

        ]
      },
      {
        path: '/menu2',
        name: 'Menu2',
        component: () => import('@/views/menu/menu2'),
        meta: { title: '援藏高校' },
        children: [
          {
            path: '/menu2-1',
            component: () => import('@/views/menu/menu2/menu2-1/complex-table'),
            name: 'Menu2-1',
            meta: { title: '学校管理' }
          },
          {
            path: '/menu2-2',
            component: () => import('@/views/menu/menu2/menu2-2'),
            name: 'Menu2-2',
            meta: { title: '援藏需求' }
          }

        ]
      },
      {
        path: '/menu3',
        name: 'Menu3',
        meta: { title: '援藏工作' },
        component: () => import('@/views/menu/menu3'),
        children: [
          {
            path: '/menu3-1',
            component: () => import('@/views/menu/menu3/menu3-1'),
            name: 'Menu3-1',
            meta: { title: '路线管理' }
          },
          {
            path: '/menu3-2',
            component: () => import('@/views/menu/menu3/menu3-2'),
            name: 'Menu3-2',
            meta: { title: '工作动态' }
          },
          {
            path: '/menu3-3',
            component: () => import('@/views/menu/menu3/menu3-3/complex-table'),
            name: 'Menu3-3',
            meta: { title: '援藏政策' }
          }
        ]
      },
      {
        path: '/menu4',
        name: 'Menu4',
        meta: { title: '招聘管理' },
        children: [
          {
            path: '/menu4-1',
            component: () => import('@/views/menu/menu4/menu4-1'),
            name: 'Menu4-1',
            meta: { title: '援藏人员' }
          },
          {
            path: '/menu4-2',
            component: () => import('@/views/menu/menu4/menu4-2'),
            name: 'Menu4-2',
            meta: { title: '人才引进' }
          }

        ]
      }
    ]
  },

  {
    path: 'external-link',
    component: Layout,
    children: [
      {
        path: 'https://panjiachen.github.io/vue-element-admin-site/#/',
        meta: { title: 'External Link', icon: 'link' }
      }
    ]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
