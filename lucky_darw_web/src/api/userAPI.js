import http from '../utils/http'

const serviceId = '/ld-lucky'
/**
 * api
 */
const resourceApi = `${serviceId}/v1/user`

export default {

  login (data) {
    const url = `${resourceApi}/login`
    return http.post(url, data)
  },
  register (data) {
    const url = `${resourceApi}/register`
    return http.post(url, data)
  },
  me () {
    const url = `${resourceApi}/me`
    return http.get(url)
  }
}
