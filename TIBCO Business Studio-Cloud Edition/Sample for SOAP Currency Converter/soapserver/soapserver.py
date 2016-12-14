from spyne import Application, rpc, ServiceBase, Iterable, Integer, Unicode

from spyne.protocol.soap import Soap11
from spyne.server.wsgi import WsgiApplication

import requests


class CurrenyConverterService(ServiceBase):
    @rpc(Unicode, Unicode, _returns=Iterable(Unicode))
    def getConversionRate(ctx, fromCurrency, toCurrency):
        url = 'http://api.fixer.io/latest?base=' + fromCurrency
        response = requests.get(url)
        #print('--    ')
        #print('date ' + response.json()['date'])
        #print('fromCurrency ' + fromCurrency)
        #print('toCurrency ' + toCurrency)
        yield response.json()['date']
        yield fromCurrency
        yield toCurrency
        if fromCurrency == toCurrency:
            #print('1')
            yield '1'
        else:
            #print(response.json()['rates'][toCurrency])
            yield str(response.json()['rates'][toCurrency])
        print('--    ')

application = Application([CurrenyConverterService], 'tibco.tci.samples.ccsoap',
                          in_protocol=Soap11(validator='lxml'),
                          out_protocol=Soap11())

wsgi_application = WsgiApplication(application)


if __name__ == '__main__':
    import logging

    from wsgiref.simple_server import make_server

    logging.basicConfig(level=logging.DEBUG)
    logging.getLogger('spyne.protocol.xml').setLevel(logging.DEBUG)

    #logging.info("listening to http://127.0.0.1:8000")
    #logging.info("wsdl is at: http://localhost:8000/?wsdl")
    logging.info('Server started on port 1884')

    server = make_server('0.0.0.0', 1884, wsgi_application)
    server.serve_forever()
