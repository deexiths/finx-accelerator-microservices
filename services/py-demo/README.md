# py-demo

Dummy Python app for Nx monorepo testing.

## Quickstart
```bash
# Create and activate a virtual environment
python -m venv .venv && source .venv/bin/activate  # Windows: .venv\Scripts\activate

# Install dependencies
pip install -U pip
pip install .[dev]

# Run CLI help
python -m py_demo.main --help

# Run tests
pytest -q

# Run API
uvicorn py_demo.api:app --reload --host 0.0.0.0 --port 8000
```

API Endpoints:
- GET http://localhost:8000/health
- GET http://localhost:8000/meaning
- POST http://localhost:8000/sum   body: {"values":[1,2,3]}
