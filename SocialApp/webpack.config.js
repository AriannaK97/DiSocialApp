const use = require("use");
module.exports = {
    entry: './src/main/resources/templates/App.js',
    devtool: 'source-map',
    cache: true,
    mode: 'development',
    output: {
        path: __dirname,
        filename: './react-app.js'
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