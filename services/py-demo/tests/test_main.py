from py_demo import meaning_of_life, sum_array

def test_meaning():
    assert meaning_of_life() == 42

def test_sum_array():
    assert sum_array([1, 2, 3]) == 6
