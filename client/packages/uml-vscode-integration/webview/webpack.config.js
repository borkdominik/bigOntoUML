// @ts-check
const path = require('path');

const outputPath = path.resolve(__dirname, '../extension/webview');

/**@type {import('webpack').Configuration}*/
const config = {
    target: 'web',

    entry: path.resolve(__dirname, 'src/index.ts'),
    output: {
        filename: 'webview.js',
        path: outputPath,
        clean: true
    },
    devtool: 'eval-source-map',
    mode: 'development',

    resolve: {
        fallback: {
            fs: false,
            net: false
        },
        extensions: ['.ts', '.tsx', '.js']
    },
    module: {
        rules: [
            {
                test: /\.[jt]sx?$/,
                loader: 'esbuild-loader'
            },
            {
                test: /\.(png|svg|jpg|jpeg|gif)$/i,
                type: 'asset/resource',
                generator: {
                    filename: 'resources/images/[hash][ext][query]'
                }
            },
            {
                test: /\.css$/,
                exclude: /(codicon|\.useable)\.css$/,
                use: ['style-loader', 'css-loader']
            },
            {
                test: /codicon.css$/,
                use: ['ignore-loader']
            }
        ]
    },
    ignoreWarnings: [/Failed to parse source map/, /Can't resolve .* in '.*ws\/lib'/]
};

module.exports = config;
