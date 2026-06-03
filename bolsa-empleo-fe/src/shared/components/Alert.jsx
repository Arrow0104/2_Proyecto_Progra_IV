// bolsa-empleo-fe/src/shared/components/Alert.jsx
export default function Alert({ tipo = 'error', mensaje }) {
    if (!mensaje) return null
    const colores = {
        error:   { background: '#fee', border: '#f88', color: '#900' },
        success: { background: '#efe', border: '#8c8', color: '#060' },
        info:    { background: '#eef', border: '#88f', color: '#006' }
    }
    const s = colores[tipo] || colores.info
    return (
        <div style={{ padding: '0.75rem', border: `1px solid ${s.border}`,
            background: s.background, color: s.color, borderRadius: '4px', marginBottom: '1rem' }}>
            {mensaje}
        </div>
    )
}