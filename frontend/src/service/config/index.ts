let BASE_URL = ''
if (import.meta.env.PROD) {
  BASE_URL = 'http://152.136.185.210:4000/'
} else {
  BASE_URL = 'http://localhost:8888/'
}

export const TIME_OUT = 10000
export { BASE_URL }
