const use = require("use");
module.exports = {
    devtool: 'source-map',
    mode: "development",
    output: {
        filename: 'react-app.js'
    },
    module: {
        rules: [{
            test: /\.(js|jsx)$/,
            exclude: /node_modules/,
            loader: "babel-loader",
            options: {
                presets: ['@babel/preset-env', '@babel/preset-react']
            }
        },{
            test: /\.css$/,
            use: [
                // [style-loader](/loaders/style-loader)
                { loader: 'style-loader' },
                // [css-loader](/loaders/css-loader)
                {
                    loader: 'css-loader',
                    options: {
                        modules: true
                    }
                },
                // [sass-loader](/loaders/sass-loader)
                { loader: 'sass-loader' }
            ]
        }
        ]
    },

    resolve: {
        extensions: ['', '.js', '.jsx', '.css']
    }
};