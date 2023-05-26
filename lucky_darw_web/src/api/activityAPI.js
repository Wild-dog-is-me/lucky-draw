import http from '../utils/http'

const serviceId = '/ld-lucky'
/**
 * api
 */
const resourceApi = `${serviceId}/v1/activity`

export default {

  list (data) {
    const url = `${resourceApi}/page`
    return http.post(url, data)
  },
  one (data) {
    const url = `${resourceApi}/one`
    return http.get(url, data)
  },
  draw (data) {
    const url = `${resourceApi}/draw`
    return http.get(url, data)
  }
}
