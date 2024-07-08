/** @type {import('next').NextConfig} */
const nextConfig = {
  reactStrictMode: false,
  images: {
    loader: 'custom',
    path: '/',
  },
  async rewrites() {
    return [
      {
        source: '/api/:path*',
        destination: 'http://api:8080/api/:path*'
      }
    ]
  },
};

export default nextConfig;
