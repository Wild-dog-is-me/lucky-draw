import http from '../utils/http'

const serviceId = '/ld-lucky'
/**
 * api
 */
const resourceApi = `${serviceId}/v1/record`

export default {

  list (data) {
    const url = `${resourceApi}/page`
    return http.post(url, data)
  }
}
