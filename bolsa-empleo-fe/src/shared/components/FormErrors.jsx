// bolsa-empleo-fe/src/shared/components/FormErrors.jsx
export default function FormErrors({ errores }) {
    if (!errores || errores.length === 0) return null
    return (
        <ul style={{ color: '#900', background: '#fee', padding: '0.5rem 1rem', borderRadius: '4px' }}>
            {errores.map((e, i) => <li key={i}>{e}</li>)}
        </ul>
    )
}