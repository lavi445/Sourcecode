import pygame
import os
import random
import sys

# Initialize Pygame
pygame.init()

# Constants
WIDTH, HEIGHT = 800, 600
FPS = 30
IMAGE_DIR = 'images'

# Colors
WHITE = (255, 255, 255)
BLACK = (0, 0, 0)

# Set up the display
screen = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("Image Compare Game")
clock = pygame.time.Clock()

# Load images
def load_images(directory):
    supported_formats = ['.png', '.jpg', '.jpeg', '.bmp', '.gif']
    images = []
    for file in os.listdir(directory):
        if os.path.splitext(file)[1].lower() in supported_formats:
            img = pygame.image.load(os.path.join(directory, file))
            img = pygame.transform.scale(img, (300, 300))
            images.append(img)
    return images

images = load_images(IMAGE_DIR)

if len(images) < 2:
    print("Please add at least two images to the 'images' folder.")
    pygame.quit()
    sys.exit()

# Game Variables
score = 0
font = pygame.font.SysFont(None, 36)
game_over = False

def draw_text(text, font, color, surface, x, y):
    textobj = font.render(text, True, color)
    rect = textobj.get_rect(center=(x, y))
    surface.blit(textobj, rect)

def main():
    global score, game_over
    running = True
    first_image = random.choice(images)
    second_image = random.choice(images)
    while running:
        clock.tick(FPS)
        screen.fill(WHITE)
        
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                running = False
            if event.type == pygame.KEYDOWN and not game_over:
                if event.key == pygame.K_y:
                    if first_image == second_image:
                        score += 1
                    else:
                        game_over = True
                elif event.key == pygame.K_n:
                    if first_image != second_image:
                        score += 1
                    else:
                        game_over = True
                if not game_over:
                    first_image = random.choice(images)
                    second_image = random.choice(images)
        
        # Display Images
        screen.blit(first_image, (100, 150))
        screen.blit(second_image, (500, 150))
        
        # Display Instructions
        draw_text("Are these images the SAME? (Y/N)", font, BLACK, screen, WIDTH//2, 50)
        draw_text(f"Score: {score}", font, BLACK, screen, WIDTH//2, 100)
        
        if game_over:
            draw_text("Game Over!", font, (255, 0, 0), screen, WIDTH//2, HEIGHT//2)
            draw_text(f"Final Score: {score}", font, BLACK, screen, WIDTH//2, HEIGHT//2 + 40)
            draw_text("Press ESC to Exit", font, BLACK, screen, WIDTH//2, HEIGHT//2 + 80)
            for event in pygame.event.get():
                if event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_ESCAPE:
                        running = False
        
        pygame.display.flip()
    
    pygame.quit()

if __name__ == "__main__":
    main()
