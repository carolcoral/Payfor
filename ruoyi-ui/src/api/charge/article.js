import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/pay/charge/list',
    method: 'get',
    params: query
  })
}

export function createArticle(data) {
  return request({
    url: '/pay/charge/create',
    method: 'post',
    data
  })
}

