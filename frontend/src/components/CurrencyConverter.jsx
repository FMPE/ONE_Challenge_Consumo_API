import { useState } from 'react';
import './CurrencyConverter.css';

export default function CurrencyConverter() {
  const [baseCurrency, setBaseCurrency] = useState('USD');
  const [targetCurrency, setTargetCurrency] = useState('ARS');
  const [amount, setAmount] = useState('');
  const [result, setResult] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const currencies = ['USD', 'PEN', 'CLP', 'ARS', 'BRL', 'COP'];

  const handleConvert = async (e) => {
    e.preventDefault();
    
    if (!amount || amount <= 0) {
      setError('Por favor ingresa un monto válido');
      return;
    }

    setLoading(true);
    setError(null);
    setResult(null);

    try {
      const response = await fetch('http://localhost:8080/api/convert', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          baseCurrency,
          targetCurrency,
          amount: parseFloat(amount),
        }),
      });

      if (!response.ok) {
        throw new Error('Error en la conversión');
      }

      const data = await response.json();
      setResult(data);
    } catch (err) {
      setError(err.message || 'Error al conectar con el servidor');
    } finally {
      setLoading(false);
    }
  };

  const handleSwapCurrencies = () => {
    setBaseCurrency(targetCurrency);
    setTargetCurrency(baseCurrency);
  };

  return (
    <div className="converter-container">
      <h1>💱 Conversor de Moneda</h1>
      
      <form onSubmit={handleConvert} className="converter-form">
        <div className="input-group">
          <label>Moneda Base</label>
          <select 
            value={baseCurrency} 
            onChange={(e) => setBaseCurrency(e.target.value)}
            className="select-input"
          >
            {currencies.map(curr => (
              <option key={curr} value={curr}>{curr}</option>
            ))}
          </select>
        </div>

        <button 
          type="button" 
          onClick={handleSwapCurrencies}
          className="swap-btn"
          title="Intercambiar monedas"
        >
          ⇄
        </button>

        <div className="input-group">
          <label>Moneda Destino</label>
          <select 
            value={targetCurrency} 
            onChange={(e) => setTargetCurrency(e.target.value)}
            className="select-input"
          >
            {currencies.map(curr => (
              <option key={curr} value={curr}>{curr}</option>
            ))}
          </select>
        </div>

        <div className="input-group full-width">
          <label>Cantidad</label>
          <input 
            type="number" 
            value={amount}
            onChange={(e) => setAmount(e.target.value)}
            placeholder="0.00"
            className="number-input"
            step="0.01"
            min="0"
          />
        </div>

        <button 
          type="submit" 
          disabled={loading}
          className="convert-btn"
        >
          {loading ? 'Convirtiendo...' : 'Convertir'}
        </button>
      </form>

      {error && <div className="error-message">{error}</div>}

      {result && (
        <div className="result-container">
          <p className="result-text">
            El valor <span className="amount">{result.originalAmount.toFixed(2)}</span> <span className="currency">[{result.baseCurrency}]</span> corresponde al valor final de
          </p>
          <p className="result-value">
            {result.convertedAmount.toFixed(2)} <span className="currency">[{result.targetCurrency}]</span>
          </p>
          <p className="exchange-rate">
            Tasa de cambio: 1 {result.baseCurrency} = {result.exchangeRate.toFixed(4)} {result.targetCurrency}
          </p>
        </div>
      )}
    </div>
  );
}
