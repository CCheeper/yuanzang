import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/need/list',
    method: 'get',
    params: query
  })
}


export function fetchArticle(id) {
  return request({
    url: '/need/detail',
    method: 'get',
    params: { id }
  })
}

export function fetchPv(pv) {
  return request({
    url: '/need/pv',
    method: 'get',
    params: { pv }
  })
}

export function createNeed(data) {
  return request({
    url: '/need/create',
    method: 'post',
    data
  })
}

export function updateNeed(data) {
  return request({
    url: '/need/update',
    method: 'post',
    data
  })
}
