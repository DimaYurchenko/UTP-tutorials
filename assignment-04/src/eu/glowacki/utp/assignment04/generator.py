from faker import Faker
import sys

fake = Faker()

def generate(num: int):
    with open("data.txt", "w") as file:
        for i in range(num):
            file.write(f'{fake.name()} {fake.date_between(start_date="-60y", end_date="today")}\n')


generate(int(sys.argv[1]))




