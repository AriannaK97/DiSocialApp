'use strict';

const rest = require('../../../../node_modules/rest');
const defaultRequest = require('../../../../node_modules/rest/interceptor/defaultRequest');
const mime = require('../../../../node_modules/rest/interceptor/mime');
const uriTemplateInterceptor = require('./api/uriTemplateInterceptor');
const errorCode = require('../../../../node_modules/rest/interceptor/errorCode');
const baseRegistry = require('../../../../node_modules/rest/mime/registry');

const registry = baseRegistry.child();

registry.register('text/uri-list', require('./api/uriListConverter'));
registry.register('application/hal+json', require('../../../../node_modules/rest/mime/type/application/hal'));

module.exports = rest
    .wrap(mime, { registry: registry })
    .wrap(uriTemplateInterceptor)
    .wrap(errorCode)
    .wrap(defaultRequest, { headers: { 'Accept': 'application/hal+json' }});